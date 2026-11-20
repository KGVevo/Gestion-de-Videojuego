package Clases.ClasesObjetos;


import Clases.ClasesObjetos.Entidades.*;
import Clases.ClasesObjetos.ObjetosClass.*;
import Clases.ClasesObjetos.SistemaDeJuego.*;
import Enums.*;
import Exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class Servidor {
    /// HashSets
    private ClaseGenericaHS<Usuario> usuarios;

    /// ArrayLists
    private ClaseGenericaAL<Mundo> mundos;
    private ClaseGenericaAL<Objeto> objetos;
    private ClaseGenericaAL<Enemigo> enemigos;
    private ClaseGenericaAL<Jugador> jugadores;
    private ClaseGenericaAL<Reserva> reservas;
    private ClaseGenericaAL<Partida> partidas;

    public Servidor() {
        this.usuarios = new ClaseGenericaHS<>();

        this.reservas = new ClaseGenericaAL<>();
        this.partidas = new ClaseGenericaAL<>();
        this.mundos = new ClaseGenericaAL<>();
        this.objetos = new ClaseGenericaAL<>();
        this.enemigos = new ClaseGenericaAL<>();
        this.jugadores = new ClaseGenericaAL<>();
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////// USUARIOS: CARGA Y LOGIN ////////////////////////////////////////////////////////////////
    // Agregar Usuario. select: 1 = Usuario, 2 = Moderador, 3 = Administrador
    public boolean agregarUsuario(String nombreUsuario, String gmail, String password, int select) throws UserInvalidException {

        int id = JDBC.agregarUsuario(nombreUsuario, gmail, password, select);

        if(id == -1){
            return false;
        }

        if (select == 1) {
            Usuario usuario = new Usuario(id, nombreUsuario, gmail, E_Rol.USUARIO);
            return usuarios.agregarElemento(usuario);

        }else if (select == 2) {
            Usuario usuario = new Moderador(id, nombreUsuario, gmail);
            return usuarios.agregarElemento(usuario);

        }else if (select == 3) {
            Usuario usuario = new Administrador(id, nombreUsuario, gmail);
            return usuarios.agregarElemento(usuario);

        }

        return false;
    }

    // Descarga todos los Usuarios
    public void descargarUsuarios(){
        usuarios = JDBC.obtenerUsuarios();
    }

    // Muestra todos los Usuarios
    public String mostrarUsuarios(){
        return usuarios.mostrarElementoTodos();
    }

    // Verificar el formato del Gmail
    public static void verificarFormatoGmail(String gmailUser) throws GmailInvalidoException {
        if(!gmailUser.endsWith("@gmail.com")){
            throw new GmailInvalidoException("|=================================================|\n" +
                                             "|ERROR: !! El formato del Gmail no es correcto !! |\n" +
                                             "|=================================================|\n");
        }

        if(gmailUser.length() < 18){
            throw new GmailInvalidoException("|======================================================|\n" +
                                             "|ERROR: !! El Gmail contiene menos de 18 caracteres !! |\n" +
                                             "|======================================================|\n");
        }


    }

    // Verifica si el Gmail existe en la base de datos
    public Usuario confirmarExistenciaCuenta(String id ,String password) throws UserInvalidException, GmailInvalidoException {
        verificarFormatoGmail(id);

        if(!usuarios.verificarElemento(id)){
            throw new UserInvalidException( "|============================================|\n" +
                                            "|ERROR: !! El Gmail Ingresado no Existe !!   |\n" +
                                            "|============================================|\n");
        }

        if (!password.equals(JDBC.getUsuarioPassword(id))){
            throw new UserInvalidException("|=========================================|\n" +
                                           "|ERROR: !! La Contraseña es Incorrecta!!  |\n" +
                                           "|=========================================|\n");
        }

        // Una cuenta baneada no puede iniciar sesion
        Usuario usuario = JDBC.obtenerUsuario(id);

        if (usuario != null && usuario.estaBaneada()){
            throw new UserInvalidException("|=============================================|\n" +
                                           "|ERROR: !! Esta cuenta esta baneada !!        |\n" +
                                           "|=============================================|\n");
        }

        return usuario;
    }

    // Muestra todos los postulados a Moderador
    public String mostrarPeticionesAdministracion(){
        StringBuilder peticiones = new StringBuilder();

        for(Usuario u : usuarios.obtenerTodosElementos()){
            if((u instanceof Moderador && ((Moderador) u).getPermitido() == false) ||
                    (u instanceof Administrador && ((Administrador) u).isPermitido() == false )){

                peticiones.append(u.toString());
            }
        }

        return  peticiones.toString();
    }

    // Verifica si es el primer admin en Crear una cuenta
    public boolean verificacionExistenciaAdmin(){

        for (Usuario u : usuarios.obtenerTodosElementos()){
            if(u instanceof Administrador && u.getRol() == E_Rol.ADMINISTRADOR){
                return true;
            }
        }

        return false;
    }

    public Usuario obtenerUsuario(int id){
        Usuario usuario = usuarios.obtenerElemento(id);

        return usuario;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// METODOS JUGADORES //////////////////////////////////////////////////////////////////////

    // Busca un jugador por id de jugador
    public Jugador obtenerJugador(int idJugador) {
        return jugadores.obtenerElementoPorId(idJugador);
    }
    // Devuelve todos los jugadores que tiene un usuario
    public ArrayList<Jugador> obtenerJugadoresDeUsuario(int idUsuario) {
        ArrayList<Jugador> listUsuarios = new ArrayList<>();

        for (Jugador j : jugadores.obtenerTodosElementos()) {
            if (j.getIdUsuario() == idUsuario) {
                listUsuarios.add(j);
            }
        }

        return listUsuarios;
    }
    // Muestra todos los de jugadores de un usuario
    public String mostrarJugadoresDeUsuario(int idUsuario) {
        ArrayList<Jugador> lista = obtenerJugadoresDeUsuario(idUsuario);

        if (lista.isEmpty()) {
            return "|El usuario todavia no tiene personajes|\n";
        }

        StringBuilder sb = new StringBuilder();

        for (Jugador j : lista) {
            sb.append(j.toString()).append("\n");
        }

        return sb.toString();
    }
    // Mostrar Todos los Jugadores -> solo visible para admin
    public String mostrarJugadores() {
        return jugadores.mostrarTodosElementos();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// RESERVAS, ENTRADA Y SALIDA DE MUNDOS ///////////////////////////////////////////////

    /// Reserva el mundo para un Jugador en especifico. JUGADOR, NO USUARIO
    public boolean reservarMundo(Jugador jugador, Mundo mundo, LocalDate desde,LocalDate hasta) throws ReservaInvalidaException {
        if (jugador == null || mundo == null || desde == null || hasta == null) {
            throw new ReservaInvalidaException("|=============================================|\n" +
                                               "|ERROR: Faltan datos para realizar la reserva|\n" +
                                               "|=============================================|\n");
        }

        if (cuentaBaneada(jugador)) {
            throw new ReservaInvalidaException("|=============================================|\n" +
                                               "|ERROR: La cuenta del jugador esta baneada    |\n" +
                                               "|=============================================|\n");
        }

        if(mundo.getEstadoMundo() == E_EstadoMundo.NO_DISPONIBLE){
            throw new ReservaInvalidaException("|==================================|\n" +
                                               "|ERROR: El mundo no esta disponible|\n" +
                                               "|==================================|\n");
        }
        // Hasta > desde,
        if (!hasta.isAfter(desde)){
            throw new ReservaInvalidaException("|=====================================================|\n" +
                                               "|ERROR: La fecha final debe ser posterior a la inicial|\n" +
                                               "|=====================================================|\n");
        }
        // Desde < Ahora,
        if (desde.isBefore(LocalDate.now())) {
            throw new ReservaInvalidaException("|========================================|\n" +
                                               "|ERROR: No se puede reservar en el pasado|\n" +
                                               "|========================================|\n");
        }

        for (Reserva r : reservas.obtenerTodosElementos()) {
            // Comprueba si es la Misma Reserva
            boolean mismoMundo = r.getMundo().getID().equals(mundo.getID());
            //Comprueba si el estado de la Reserva es activo y si se Superpone en caso cotrario, se crea la reserva
            if (mismoMundo && r.getEstadoReserva() == E_EstadoReserva.ACTIVA && r.comprobarFechas(desde, hasta)) {
                throw new ReservaInvalidaException("|===================================================|\n" +
                                                   "|ERROR:   El Mundo ya esta reservado en esa Fecha   |\n" +
                                                   "|===================================================|\n");
            }
        }

        //se crea y se guarda la reserva
        int id = JDBC.agregarReserva(jugador.getID(), mundo.getID(), desde, hasta, E_EstadoReserva.ACTIVA);

        if(id == -1){
            return false;
        }

        //TODO: JDBC  -
        return reservas.agregarElemento(new Reserva(id, jugador, mundo, desde, hasta));
    }

    /// Cancela la reserva de un Jugador en base al id de la Reserva
    public boolean cancelarReserva(Jugador jugador, int idReserva) throws ReservaInvalidaException {
        Reserva reserva = reservas.obtenerElementoPorId(idReserva);

        if (reserva == null) {
            throw new ReservaInvalidaException("|==================================|\n" +
                                               "|ERROR: La reserva no existe       |\n" +
                                               "|==================================|\n");
        }

        if (jugador == null || !reserva.getJugador().getID().equals(jugador.getID())) {
            throw new ReservaInvalidaException("|=====================================================|\n" +
                                               "|ERROR: La reserva pertenece a otro Jugador           |\n" +
                                               "|=====================================================|\n");
        }

        if (!reserva.cancelarReserva()) {
            throw new ReservaInvalidaException("|=====================================================|\n" +
                                               "|ERROR: Solo se puede cancelar una reserva ACTIVA     |\n" +
                                               "|=====================================================|\n");
        }

        return true;
    }

    /// Muestra todas las reservas de un jugador (activas, canceladas y cumplidas)
    public String listarReservasDeJugador(Jugador jugador) {
        if (jugador == null) {
            return "|ERROR: Jugador invalido|\n";
        }

        StringBuilder sb = new StringBuilder();

        for (Reserva r : reservas.obtenerTodosElementos()) {
            if (r.getJugador().getID().equals(jugador.getID())) {
                sb.append(r.toString());
            }
        }

        if (sb.length() == 0) {
            return "|El jugador todavia no tiene reservas|\n";
        }

        return sb.toString();
    }

    public String mostrarReservas(){
        StringBuilder sb = new StringBuilder();

        for (Reserva r : reservas.obtenerTodosElementos()) {
            sb.append(r.toString());
        }

        return toString();
    }

    /// Busca la partida en curso de un jugador. Devuelve null si no esta jugando.
    private Partida buscarPartidaDeJugador(Jugador jugador) {
        for (Partida p : partidas.obtenerTodosElementos()) {
            if (p.partidaEnCurso() && p.getJugador().getID().equals(jugador.getID())) {
                return p;
            }
        }

        return null;
    }

    /// Metodo para entrar al mundo
    public boolean entrarMundo(Jugador jugador, Mundo mundo) throws ReservaInvalidaException, PartidaInvalidaException {
        if (cuentaBaneada(jugador)) {
            throw new PartidaInvalidaException("|=============================================|\n" +
                                               "|ERROR: La cuenta del jugador esta baneada    |\n" +
                                               "|=============================================|\n");
        }

        /// Un jugador no puede estar jugando en dos mundos a la vez
        if (buscarPartidaDeJugador(jugador) != null) {
            throw new PartidaInvalidaException("|=======================================================|\n" +
                                               "|ERROR:   El Jugador ya esta jugando en otro Mundo      |\n" +
                                               "|=======================================================|\n");
        }

        LocalDate hoy = LocalDate.now();
        Reserva reserva = null;

        for (Reserva r : reservas.obtenerTodosElementos()) {
            //Compruebo si el mundo es el mismo
            boolean mismoMundo = r.getMundo().getID().equals(mundo.getID());
            //Basicamente lo que hago es Compruebo si no hay reservas funcionando en el momento
            boolean cubreHoy = !hoy.isBefore(r.getDesde()) && hoy.isBefore(r.getHasta());
            //Si el mundo es mio Y el estado de la reserva esta ACTIVA y me cubre hoy, puedo ingresar
            if (mismoMundo && r.getEstadoReserva() == E_EstadoReserva.ACTIVA && cubreHoy) {
                if(r.getJugador().getID().equals(jugador.getID())){
                    reserva = r;
                }else{
                    throw new ReservaInvalidaException("|=======================================================|\n" +
                                                       "|ERROR:   El Mundo ya esta reservado por otro Jugador   |\n" +
                                                       "|=======================================================|\n");
                }
            }

        }


        if(!mundo.entrar(jugador)){
            throw new PartidaInvalidaException("|=====================================================|\n" +
                                               "|ERROR:   El Mundo esta:" + mundo.getEstadoMundo() + "|\n" +
                                               "|=====================================================|\n");
        }

        if(reserva != null){
            reserva.cumplirReserva();
        }

        int id = JDBC.agregarPartida(jugador.getID(), mundo.getID(), LocalDate.now(), null,  null, 0, 0 );


        if(id == -1){
            return false;
        }

        ///TODO: JDBC        -
        return partidas.agregarElemento(new Partida(id, jugador, mundo));
    }

    /// Busca la partida en curso de un mundo. Devuelve null si el mundo no tiene a nadie jugando.
    private Partida buscarPartidaDeMundo(Mundo mundo) {
        for (Partida p : partidas.obtenerTodosElementos()) {
            if (p.partidaEnCurso() && p.getMundo().getID().equals(mundo.getID())) {
                return p;
            }
        }

        return null;
    }

    /// Metodo para salir del Mundo
    public boolean salirMundo(Mundo mundo, E_ResultadoPartida resultadoPartida) {
        Partida p = buscarPartidaDeMundo(mundo);

        if (p == null) {
            return false;
        }

        if (!p.finalizarPartida(resultadoPartida, p.getExpGanada(), p.getMonedasGanadas())) {
            return false;
        }

        return mundo.salir();
    }

    /// Historial de un jugador: todas sus partidas (en curso y terminadas)
    public String listarPartidasDeJugador(Jugador jugador) {
        if (jugador == null) {
            return "|ERROR: Jugador invalido|\n";
        }

        StringBuilder sb = new StringBuilder();

        /// Recorre todas las partidas y se queda solo con las de este jugador
        for (Partida p : partidas.obtenerTodosElementos()) {
            if (p.getJugador().getID().equals(jugador.getID())) {
                sb.append(p.toString());
            }
        }

        /// Si no encontro ninguna, avisa en vez de devolver un texto vacio
        if (sb.length() == 0) {
            return "|El jugador todavia no tiene partidas|\n";
        }

        return sb.toString();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// BATALLAS ///////////////////////////////////////////////////////////////////////////

    // Arma la batalla del jugador contra el proximo enemigo del mundo.
    public Batalla prepararBatalla(Jugador jugador, Mundo mundo) throws BatallaInvalidaException {

        // 1. El jugador tiene que estar jugando en ese mundo
        if (!estaJugandoEn(jugador, mundo)) {
            throw new BatallaInvalidaException("|ERROR: El jugador no esta jugando en este mundo|\n");
        }

        // 2. Tiene que estar vivo
        if (jugador.getVida() <= 0) {
            throw new BatallaInvalidaException("|ERROR: El jugador no tiene vida para pelear|\n");
        }

        // 3. Tiene que quedar algun enemigo
        if (mundo.getEnemigosMundo().isEmpty()) {
            throw new BatallaInvalidaException("|ERROR: No quedan enemigos en este mundo|\n");
        }

        // 4. Se pelea contra una COPIA del enemigo: los enemigos del mundo son los mismos
        //    objetos del catalogo del Servidor, y la batalla les resta vida
        Enemigo original = mundo.getEnemigosMundo().get(0);

        Enemigo copia = new Enemigo(original.getID(), original.getNombreEntidad(), original.getVida(),
                original.getDanioBase(), original.getNivel(), original.getTipoDeEnemigo());

        return new Batalla(jugador, copia);
    }


    /// Termina una batalla
    public String terminarBatalla(Batalla batalla, Mundo mundo) {
        if (!batalla.getTerminada()) {
            return "|La batalla todavia no termino|\n";
        }

        /// Las consecuencias del resultado se aplican una sola vez
        if (!batalla.cerrarBatalla()) {
            return "|La batalla ya fue cerrada|\n";
        }

        Jugador jugador = batalla.getJugador();
        Enemigo enemigo = batalla.getEnemigo();

        /// Los bonus de las pociones de DANIO y CRITICO duran hasta que termina la batalla
        jugador.quitarBonus();

        switch (batalla.getResultado()) {
            case VICTORIA:
                /// La recompensa depende del nivel y del tipo de enemigo
                int multiplicador = 1;
                if (enemigo.getTipoDeEnemigo() == E_TipoDeEnemigo.ELITE) {
                    multiplicador = 2;
                } else if (enemigo.getTipoDeEnemigo() == E_TipoDeEnemigo.JEFE) {
                    multiplicador = 5;
                }

                int exp = enemigo.getNivel() * 20 * multiplicador;
                int monedas = enemigo.getNivel() * 5 * multiplicador;

                int nivelAntes = jugador.getNivel();
                jugador.modificadorDeExp(exp);
                jugador.sumarMonedas(monedas);

                // La partida en curso va acumulando lo ganado
                Partida partida = buscarPartidaDeJugador(jugador);
                if (partida != null) {
                    partida.sumarRecompensa(exp, monedas);
                }

                // Al ganar recupera 20 de vida. Si subio de nivel, se cura por completo.
                jugador.curar(20);
                boolean subioDeNivel = jugador.getNivel() > nivelAntes;
                if (subioDeNivel) {
                    jugador.curar(100);
                }

                // El enemigo vencido sale del mundo
                mundo.eliminarEnemigoVencido(enemigo);

                String texto = "|Ganaste " + exp + " de exp y " + monedas + " monedas|\n";
                if (subioDeNivel) {
                    texto += "|Subiste al nivel " + jugador.getNivel() + ": te curaste por completo|\n";
                }
                if (mundo.getEnemigosMundo().isEmpty()) {
                    texto += "|Derrotaste a todos los enemigos del mundo|\n";
                }
                return texto;

            case DERROTA:
                /// La partida termina como DERROTA, el mundo se libera y el jugador revive con 50 de vida
                salirMundo(mundo, E_ResultadoPartida.DERROTA);
                jugador.curar(50);
                return "|Fuiste derrotado: la partida termino y revivis con 50 de vida|\n";

            default:
                return "|Huiste de la batalla|\n";
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// ADMINISTRACION DE USUARIOS ////////////////////////////////////////////////////////


    /// Busca un usuario por su ID numerico
    private Usuario buscarUsuarioPorId(int idUsuario) {
        for (Usuario u : usuarios.obtenerTodosElementos()) {
            if (u.getIdUsuario().equals(idUsuario)) {
                return u;
            }
        }

        return null;
    }

    /// Verifica que el usuario tenga el permiso pedido; si no, corta la accion con una excepcion
    private void verificarPermiso(Usuario solicitante, E_Permisos permiso) throws UserInvalidException {
        if (solicitante == null || !solicitante.tienePermiso(permiso)) {
            throw new UserInvalidException("|=====================================================|\n" +
                                             "|ERROR: No tenes permisos para realizar esta accion   |\n" +
                                            "|=====================================================|\n");
        }
    }

    /// Le damos permisos a un Moderador en base al Gmail
    public boolean verificarModerador(Usuario solicitante, String gmailDelPermitido) throws UserInvalidException {
        verificarPermiso(solicitante, E_Permisos.ASIGNAR_PERMISOS);

        Usuario user = usuarios.obtenerElemento(gmailDelPermitido);

        if(user == null) {
            return false;
        }

        /// Solo un Moderador puede ser aprobado.
        if ((user instanceof Moderador && ((Moderador) user).getPermitido() == false) ||
                (user instanceof Administrador && ((Administrador) user).isPermitido() == false)) {

            return ((Moderador) user).aprobar() || ((Administrador) user).aprobar();
        }

        return false;
    }

    /// Dice si la cuenta duena de ese jugador esta baneada (si el usuario no esta cargado en el Servidor, no se bloquea)
    private boolean cuentaBaneada(Jugador jugador) {
        Usuario propietario = buscarUsuarioPorId(jugador.getIdUsuario());

        return propietario != null && propietario.estaBaneada();
    }

    /// Al banear se cancelan sus reservas activas y se lo saca de los mundos donde esta jugando.
    public boolean banearUsuario(Usuario solicitante, int idUsuario) throws UserInvalidException {
        verificarPermiso(solicitante, E_Permisos.SUSPENDER_CUENTAS);

        Usuario objetivo = buscarUsuarioPorId(idUsuario);

        if (objetivo == null) {
            return false;
        }

        if (objetivo.getRol() == E_Rol.ADMINISTRADOR || objetivo.equals(solicitante)) {
            throw new UserInvalidException("|===========================================================|\n" +
                                           "|ERROR: No se puede banear a un Administrador ni a uno mismo|\n" +
                                           "|===========================================================|\n");
        }

        /// Verificamos si ya esta baneado
        if (!objetivo.banear()) {
            return false;
        }

        /// Buscamos si tiene alguna reserva para cancelarsela
        for (Reserva r : reservas.obtenerTodosElementos()) {
            if (r.getJugador().getIdUsuario() == idUsuario) {
                r.cancelarReserva();
            }
        }

        /// Si tiene alguna partida se la cerramos por abandono
        for (Partida p : partidas.obtenerTodosElementos()) {
            if (p.partidaEnCurso() && p.getJugador().getIdUsuario() == idUsuario) {
                salirMundo(p.getMundo(), E_ResultadoPartida.ABANDONO);
            }
        }

        JDBC.cambiarEstadoUsuario(idUsuario);
        return true;
    }

    /// Le saca el ban a un usuario. Requiere el permiso SUSPENDER_CUENTAS.
    public boolean desbanearUsuario(Usuario solicitante, int idUsuario) throws UserInvalidException {
        verificarPermiso(solicitante, E_Permisos.SUSPENDER_CUENTAS);

        Usuario objetivo = buscarUsuarioPorId(idUsuario);

        if (objetivo == null || objetivo.getEstadoCuenta() == E_EstadoCuenta.ACTIVA) {
            return false;
        }

        JDBC.cambiarEstadoUsuario(objetivo.getIdUsuario());
        return objetivo.desbanear();
    }

    /// Lista los usuarios con la cuenta baneada. Requiere el permiso SUSPENDER_CUENTAS.
    public String listarUsuariosBaneados(Usuario solicitante) throws UserInvalidException {
        verificarPermiso(solicitante, E_Permisos.SUSPENDER_CUENTAS);

        StringBuilder sb = new StringBuilder();

        for (Usuario u : usuarios.obtenerTodosElementos()) {
            if (u.estaBaneada()) {
                sb.append(u.toString()).append("\n");
            }
        }

        if (sb.length() == 0) {
            return "|No hay usuarios baneados|\n";
        }

        return sb.toString();
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// ADMINISTRACION DE MUNDOS ////////////////////////////////////////////////////////

    public boolean agregarMundo(Usuario solicitante, String nombreMundo, E_Dificultad dificultad) throws UserInvalidException {
        verificarPermiso(solicitante, E_Permisos.GESTIONAR_MUNDOS);

        if (nombreMundo == null || nombreMundo.isBlank() || dificultad == null) {
            return false;
        }

        for (Mundo m : mundos.obtenerTodosElementos()) {
            if (m.getNombreMundo().equalsIgnoreCase(nombreMundo.trim())) {
                return false;
            }
        }
        ///TODO:JDBC    -
        int id = JDBC.agregarMundo(nombreMundo, dificultad, E_EstadoMundo.DISPONIBLE, null, null);

        if(id == -1) {
            return false;
        }

        return mundos.agregarElemento(new Mundo(id, nombreMundo.trim(), dificultad));
    }


    /// Busca un mundo por su ID. Devuelve null si no existe.
    public Mundo obtenerMundo(int idMundo) {
        return mundos.obtenerElementoPorId(idMundo);
    }


    /// Marcar mundo como no disponible por X razon/motivo
    public boolean marcarMundoNoDisponible(Usuario solicitante, int idMundo, E_MotivoMundo motivo, String detalle) throws UserInvalidException {
        verificarPermiso(solicitante, E_Permisos.GESTIONAR_MUNDOS);

        Mundo mundo = obtenerMundo(idMundo);

        if (detalle == null || detalle.isBlank()) {
            detalle = "Sin detalle";
        }

        if (mundo == null || motivo == null) {
            return false;
        }

        return mundo.marcarNoDisponible(motivo, detalle);
    }


    /// Pasa un Mundo que no esta disponible a -> DISPONIBLE
    public boolean marcarMundoDisponible(Usuario solicitante, int idMundo) throws UserInvalidException {
        verificarPermiso(solicitante, E_Permisos.GESTIONAR_MUNDOS);

        Mundo mundo = obtenerMundo(idMundo);

        if (mundo == null) {
            return false;
        }

        return mundo.marcarDisponible();
    }

    //******************************************************************************************************************//
    //******************************************************************************************************************//
    //******************************* Listado de Mundos ****************************************************************//

    /// Muestra todos los mundos con su estado actual
    public String listarMundos() {
        if (mundos.cantidadElementos() == 0) {
            return "|Todavia no hay mundos creados|\n";
        }

        return mundos.mostrarTodosElementos();
    }


    /// Recorre los mundos y se queda con los que estan en el estado pedido
    private String listarMundosPorEstado(E_EstadoMundo estado, String mensajeVacio) {
        StringBuilder sb = new StringBuilder();

        for (Mundo m : mundos.obtenerTodosElementos()) {
            if (m.getEstadoMundo() == estado) {
                sb.append(m.toString());
            }
        }

        if (sb.length() == 0) {
            return mensajeVacio;
        }

        return sb.toString();
    }


    /// Muestra los mundos DISPONIBLES (los que se pueden reservar o a los que se puede entrar)
    public String listarMundosDisponibles() {
        return listarMundosPorEstado(E_EstadoMundo.DISPONIBLE, "| !! No hay mundos disponibles !! |\n");
    }

    /// Muestra los mundos NO_DISPONIBLES, cada uno con su motivo y detalle
    public String listarMundosNoDisponibles() {
        return listarMundosPorEstado(E_EstadoMundo.NO_DISPONIBLE, "| !! No hay mundos no disponibles !! |\n");
    }

    public String listarMundosReservados(){
        return listarMundosPorEstado(E_EstadoMundo.RESERVADO, "| !! No hay mundos reservados !! |\n");
    }


    /// Muestra los mundos Ocupados con los datos de los Usuarios
    public String listarMundosOcupados(Usuario solicitante) throws UserInvalidException {
        verificarPermiso(solicitante, E_Permisos.GESTIONAR_MUNDOS);

        StringBuilder sb = new StringBuilder();

        for (Mundo m : mundos.obtenerTodosElementos()) {
            if (m.getEstadoMundo() == E_EstadoMundo.EN_PARTIDA && m.getJugadorActual() != null) {
                Jugador jugador = m.getJugadorActual();
                Usuario propietario = buscarUsuarioPorId(jugador.getIdUsuario());

                sb.append(m.toString());
                sb.append("   Jugador: ").append(jugador.toString()).append("\n");

                if (propietario != null) {
                    sb.append("   Usuario: ").append(propietario.toString()).append("\n");
                }
            }
        }

        if (sb.length() == 0) {
            return "|No hay mundos ocupados en este momento|\n";
        }

        return sb.toString();
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FASES, TIENDA E INVENTARIO /////////////////////////////////////////////////////

    /// Dice si el jugador esta jugando ahora mismo dentro de ese mundo
    private boolean estaJugandoEn(Jugador jugador, Mundo mundo) {
        return mundo.getEstadoMundo() == E_EstadoMundo.EN_PARTIDA
                && mundo.getJugadorActual() != null
                && mundo.getJugadorActual().getID().equals(jugador.getID());
    }

    /// Generar Datos del Mundo (enemigos segun el nivel del jugador y tienda)
    public Mundo generarMundo(Mundo mundoCreado,Jugador jugador) throws RangoException, ObjetoInvalidoException {
        // Se generan los Enemigos
        mundoCreado.modificarEnemigos(generarEnemigosMundo(mundoCreado, jugador.getNivel()));
        // Se genera la tienda
        mundoCreado.modificarTienda(generarTienda(mundoCreado));

        return mundoCreado;
    }


    /// Solo se puede cuando no quedan enemigos en la fase actual.
    /// Devuelve false si el mundo ya estaba en la fase final: en ese caso el jugador completo el mundo
    /// y hay que cerrarBatalla la partida con salirMundo(mundo, VICTORIA).
    public boolean avanzarFase(Jugador jugador, Mundo mundo) throws PartidaInvalidaException, RangoException, ObjetoInvalidoException {
        if (!estaJugandoEn(jugador, mundo)) {
            throw new PartidaInvalidaException("|ERROR: El jugador no esta jugando en este mundo|\n");
        }

        if (!mundo.getEnemigosMundo().isEmpty()) {
            throw new PartidaInvalidaException("|ERROR: Todavia quedan enemigos en esta fase|\n");
        }

        if (!mundo.avanzarFace()) {
            return false;
        }

        generarMundo(mundo, jugador);
        return true;
    }

    /// Muestra los objetos de la tienda del mundo con su precio
    public String mostrarTienda(Mundo mundo) {
        if (mundo.getTiendaObjetos().isEmpty()) {
            return "|La tienda esta vacia|\n";
        }

        StringBuilder sb = new StringBuilder();

        for (Objeto o : mundo.getTiendaObjetos()) {
            sb.append(o.toString()).append(" | Precio: ").append(o.getPrecio()).append("\n");
        }

        return sb.toString();
    }


    /// Primero se intenta guardar en el inventario (puede fallar por falta de espacio o por estar repetido)
    public String comprarObjeto(Jugador jugador, Mundo mundo, int idObjeto)
            throws PartidaInvalidaException, ObjetoInvalidoException, InventarioException {
        if (!estaJugandoEn(jugador, mundo)) {
            throw new PartidaInvalidaException("|ERROR: El jugador no esta jugando en este mundo|\n");
        }

        Objeto objeto = null;
        for (Objeto o : mundo.getTiendaObjetos()) {
            if (o.getID().equals(idObjeto)) {
                objeto = o;
                break;
            }
        }

        if (objeto == null) {
            throw new ObjetoInvalidoException("|-> Ese objeto no esta en la tienda <-|");
        }

        if (jugador.getMonedas() < objeto.getPrecio()) {
            throw new ObjetoInvalidoException("|-> No te alcanzan las monedas (precio: " + objeto.getPrecio() + ") <-|");
        }

        jugador.getInventario().agregarObjetoInventario(objeto);
        jugador.gastarMonedas(objeto.getPrecio());
        mundo.getTiendaObjetos().remove(objeto);

        return "|Compraste " + objeto.getNombre() + " por " + objeto.getPrecio() + " monedas|\n";
    }

    /// Equipa un arma que el jugador tiene en el inventario. El arma sigue en el inventario.
    public boolean equiparDesdeInventario(Jugador jugador, int idObjeto) throws ObjetoInvalidoException, ClaseAsociadaException {
        Objeto objeto = jugador.getInventario().buscarItem(idObjeto);

        if (objeto == null) {
            throw new ObjetoInvalidoException("|-> Ese objeto no esta en tu inventario <-|");
        }

        if (!(objeto instanceof Arma)) {
            throw new ObjetoInvalidoException("|-> Ese objeto no es un arma <-|");
        }

        return ((Arma) objeto).equipar(jugador);
    }

    /// Usa una pocion del inventario. Si tuvo efecto, se consume y sale del inventario.
    public String usarPocion(Jugador jugador, int idObjeto) throws ObjetoInvalidoException, ClaseAsociadaException {
        Objeto objeto = jugador.getInventario().buscarItem(idObjeto);

        if (objeto == null) {
            throw new ObjetoInvalidoException("|-> Ese objeto no esta en tu inventario <-|");
        }

        if (!(objeto instanceof Pocion)) {
            throw new ObjetoInvalidoException("|-> Ese objeto no es una pocion <-|");
        }

        String resultado = ((Pocion) objeto).usar(jugador);
        jugador.getInventario().borrarObjetoInventario(idObjeto);

        return resultado;
    }



    ////////////////////////////////// AGREGAR ELEMENTOS METODOS ///////////////////////////////////////////////////////




    /// Opcion: 1 = Cuerpo a Cuerpo, 2 = Distancia, 3 = Mago.
    /// Cada jugador nace con el arma inicial de su clase.
    public boolean agregarJugador(String nombreJugador, int idUsuario, int opcion) {
        int id = -1;

        /// Se verifica que la opcion sea valida
        if (opcion < 1 || opcion > 3) {
            return false;
        }


        if (opcion == 1) {
            id = JDBC.agregarJugador(idUsuario, nombreJugador, E_ClaseAsociada.CUERPO_A_CUERPO);
        }else if (opcion == 2) {
            id = JDBC.agregarJugador(idUsuario, nombreJugador, E_ClaseAsociada.DISTANCIA);
        }else  if (opcion == 3) {
            id = JDBC.agregarJugador(idUsuario, nombreJugador, E_ClaseAsociada.MAGO);
        }


        if (id == -1) {
            return false;
        }

        Jugador jugador = null;




        ///TODO: JDBC    -

        switch (opcion) {
            case 1:
                ArmaCuerpoACuerpo arma = (ArmaCuerpoACuerpo) obtenerArma(1);
                jugador = new JugadorCuerpoACuerpo(id, nombreJugador, idUsuario, arma);
                break;
            case 2:
                ArmaDistancia armaD = (ArmaDistancia)  obtenerArma(2);
                jugador = new JugadorDistancia(id, nombreJugador, idUsuario, armaD);
                break;
            default:
                ArmaMago armaM = (ArmaMago) obtenerArma(3);
                jugador = new JugadorMago(id, nombreJugador, idUsuario, armaM);
                break;
        }

        return jugadores.agregarElemento(jugador);
    }

    /// Agregar Objeto
    public boolean agregarObjeto(String nombre, int nivel, E_Calidad calidad){

        ///TODO: JDBC   -
        int id = JDBC.agregarObjeto(1, nombre, nivel, calidad, "No hay Descripcion",0,0, 0);

        if(id == -1){
            return false;
        }

        return objetos.agregarElemento(new Objeto(id, nombre, nivel, calidad)); /// Esta funcion crea objetos que no tienen ninguna funcionalidad (huesos, piedra, palo, etc)
    }

    /// Agregar Arma
    public boolean agregarArma(String nombre, int nivel, int danioBase, E_Calidad calidad, int consumo, E_ClaseAsociada claseAsociada) {
        int id = -1;
        switch (claseAsociada) {
            case CUERPO_A_CUERPO: id = JDBC.agregarObjeto(2, nombre, nivel, calidad, "No hay descripcion", danioBase , consumo, 0); break;
            case DISTANCIA: id = JDBC.agregarObjeto(3, nombre, nivel, calidad, "No hay descripcion", danioBase, consumo, 0); break;
            case MAGO: id = JDBC.agregarObjeto(4, nombre, nivel, calidad, "No hay descripcion", danioBase, consumo, 0); break;
        }


        if(id == -1){
            return false;
        }


        ///TODO: JDBC    -
        if(claseAsociada == E_ClaseAsociada.CUERPO_A_CUERPO) {
            return objetos.agregarElemento(new ArmaCuerpoACuerpo(id, nombre, nivel, danioBase, calidad, consumo));
        }
        else if(claseAsociada == E_ClaseAsociada.DISTANCIA) {
            return objetos.agregarElemento(new ArmaDistancia(id, nombre, nivel, danioBase, calidad, consumo));
        }
        else if(claseAsociada == E_ClaseAsociada.MAGO) {
            return objetos.agregarElemento(new ArmaMago(id, nombre, nivel, danioBase, calidad, consumo));
        }

        return false;
    }

    /// Agregar Pocion
    public boolean agregarPocion( String nombre, int nivel, double efecto, E_TipoPocion tipoPocion, E_Calidad calidad) throws RangoException {
        ///TODO: Falta agreagar el ID proveniente del JDBC   -
        int tipoPocionInt = 0;

        switch (tipoPocion) {
            case CURACION: tipoPocionInt  = 1; break;
            case MANA: tipoPocionInt = 2; break;
            case ENERGIA: tipoPocionInt = 3; break;
            case DANIO: tipoPocionInt = 4; break;
            case CRITICO: tipoPocionInt = 5; break;
        }



        int id = JDBC.agregarObjeto(5, nombre, nivel, calidad, "No hay descripcion", 0, 0, tipoPocionInt);

        return objetos.agregarElemento(new Pocion(id, nombre, nivel, efecto, tipoPocion, calidad));
    }

    public Arma obtenerArma(int id){
        Objeto arma = JDBC.obtenerObjeto(id);

        if(arma instanceof ArmaCuerpoACuerpo){
            return (ArmaCuerpoACuerpo) arma;
        }
        if (arma instanceof ArmaDistancia){
            return (ArmaDistancia) arma;
        }
        if (arma instanceof ArmaMago){
            return (ArmaMago) arma;
        }

        return null;
    }

    ///////////////////////////// ELIMINAR ELEMENTOS METODOS ///////////////////////////////////////////////////////////

    public boolean eliminarUsuario(int id){
        int afectado = JDBC.eliminarUsuario(id);
        if(afectado != -1){
            /// El set guarda los usuarios por gmail (su getID()), por eso se busca el usuario por su ID numerico
            Usuario usuario = buscarUsuarioPorId(id);

            if(usuario != null){
                usuarios.eliminarElemento(usuario);
            }
            return true;
        }
        return false;
    }

    /// Eliminar Objeto
    public boolean eliminarObjeto(int id){
        return objetos.eliminarElemento(id);
    }

    /// Eliminar Enemigo
    public boolean eliminarEnemigo(int id){
        int afectado = JDBC.eliminarEnemigo(id);

        if(afectado != -1){
            return enemigos.eliminarElemento(id);
        }

        return false;
    }

    /// Elimina un Jugador por id de Jugador
    public boolean eliminarJugador(int idJugador) {
        return jugadores.eliminarElemento(idJugador);
    }

    /// Elimina un Jugador por Elemento Jugador
    public boolean eliminarJugador(Jugador j) {
        return jugadores.eliminarElemento(j);
    }


    ///////////////////////// MOSTRAR ELEMENTOS METODOS ////////////////////////////////////////////////////////////////////

    public String mostrarObjetoID(int id) {
        Objeto objeto = objetos.obtenerElementoPorId(id);

        if (objeto == null) {
            return "|El objeto no existe|\n";
        }

        return objeto.toString();
    }

    public String mostrarObjetoTodos() {
        return objetos.mostrarTodosElementos();
    }

    /// Muestra un enemigo -> solo para el admin
    public String mostrarEnemigoID(int id) {
        Enemigo enemigo = enemigos.obtenerElementoPorId(id);

        if (enemigo == null) {
            return "|El enemigo no existe|\n";
        }

        return enemigo.toString();
    }








    ////////////////////////// GENERACION DE ENEMIGOS Y TIENDA DEL MUNDO ///////////////////////////////////////////////

    /// Generador de tienda para Mundo
    private ArrayList<Objeto> generarTienda(Mundo mundoCreado)throws ObjetoInvalidoException {
        ArrayList<Objeto> aux = new ArrayList<>();
        /// Se trabaja con la lista (posiciones 0, 1, 2...) porque obtenerElementoPorId busca por ID, no por posicion
        ArrayList<Objeto> catalogo = objetos.obtenerTodosElementos();

        if(catalogo.size() < mundoCreado.getCantidadObjetosTienda()){
            throw new ObjetoInvalidoException("|No hay suficientes objetos para rellenar la Tienda|");
        }


        for(int i = 0; i < mundoCreado.getCantidadObjetosTienda(); i++){
            /// remove (y no get) para no repetir el mismo objeto en la tienda
            aux.add(catalogo.remove(SistemaDeProbabilidad.generadorInt(catalogo.size())));
        }

        return aux;
    }


    /// Metodo para generar enemigos para el mundo
    private ArrayList<Enemigo> generarEnemigosMundo(Mundo mundoCreado, int nivelPlayer) throws RangoException {
        // Creamos un Arraylist auxiliar para guardar los enemigos filtrados
        ArrayList<Enemigo> auxiliar = new ArrayList<>();
        // Se trabaja con la lista (posiciones 0, 1, 2...) porque obtenerElementoPorId busca por ID, no por posicion
        ArrayList<Enemigo> catalogo = enemigos.obtenerTodosElementos();
        int i = 0;
        // Calculamos la cantidad de enemigos totales
        int cantidadEnemigos = mundoCreado.getDificultad().obtenerCantidadEnemigos() + mundoCreado.getFase().obtenerNumeroEnemigos();

        if(cantidadEnemigos > catalogo.size()){
            throw new RangoException("| !! No hay suficientes enemigos para jugar en esta Dificultad !! |");
        }

        // Filtra a los enemigos por el nivel del jugador y se queda con la cantidad justa
        while(auxiliar.size() < cantidadEnemigos && i < catalogo.size()){
            if(catalogo.get(i).getNivel() <= nivelPlayer){
                auxiliar.add(catalogo.get(i));
            }
            i++;
        }

        if(auxiliar.isEmpty()){
            throw new RangoException("| !! No hay enemigos para el nivel del jugador !! |");
        }

        return auxiliar;
    }





    public String mostrarEnemigos(Mundo mundoCreado) {
        StringBuilder lista = new StringBuilder();

        /// Muestra los enemigos que tiene ESTE mundo
        for (Enemigo e : mundoCreado.getEnemigosMundo()) {
            lista.append(e.toString());
        }

        return lista.toString();
    }






}