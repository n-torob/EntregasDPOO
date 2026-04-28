package Persistencia;

import Mundo.*;
import java.io.*;
import java.util.*;

public class PersistenciaProyecto {

    private static final String RUTA = "data/";
    private static final String SEP = ",";
    private static final String SEP_LISTA = ";";

    public PersistenciaProyecto() {
        new File(RUTA).mkdirs();
    }

    // --- PERSISTENCIA JUEGOS DE MESA ---
    
    
    //SE GUARDA LA INFORMACION DEL JUEGO DE MESA
    public void guardarJuegosDeMesa(ArrayList<JuegoDeMesa> juegos) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "juegos.txt"));
        for (JuegoDeMesa j : juegos) {
            pw.println(j.getIdJuego() + SEP + j.getNombre() + SEP + j.getAnioPublicacion() + SEP
                    + j.getEmpresaMatriz() + SEP + j.getNumJugadoresMin() + SEP + j.getNumJugadoresMax() + SEP
                    + j.getEdadMinima() + SEP + j.getCategoria().name() + SEP + j.isEsDificil() + SEP
                    + j.getEstadoJuego().name());
        }
        pw.close();
    }

    //SE CARGAN LOS JUEGOS DE MESA
    public ArrayList<JuegoDeMesa> cargarJuegosDeMesa() throws IOException {
        ArrayList<JuegoDeMesa> juegos = new ArrayList<>();
        File f = new File(RUTA + "juegos.txt");
        if (!f.exists()) return juegos;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            juegos.add(new JuegoDeMesa(p[0], p[1], Integer.parseInt(p[2]), p[3],
                    Integer.parseInt(p[4]), Integer.parseInt(p[5]), Integer.parseInt(p[6]),
                    CategoriaJuego.valueOf(p[7]), Boolean.parseBoolean(p[8]), EstadoJuego.valueOf(p[9])));
        }
        br.close();
        return juegos;
    }

    private JuegoDeMesa buscarJuego(String id, ArrayList<JuegoDeMesa> juegos) {
        for (JuegoDeMesa j : juegos) {
            if (j.getIdJuego().equals(id)) return j;
        }
        return null;
    }

    // --- PERSISTENCIA DEL INVENTARIO PRESTAMO ---
    
    //SE GUARDA EL INVENTARIO DE LOS JUEGOS PARA PRESTAMO
    public void guardarInventarioPrestamo(ArrayList<JuegoPrestamo> inventario) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "inventario_prestamo.txt"));
        for (JuegoPrestamo jp : inventario) {
            pw.println(jp.getJuego().getIdJuego() + SEP + jp.getCopiasDisponibles() + SEP + jp.getCopiasPrestadas());
        }
        pw.close();
    }

    //SE CARGA EL INVENTARIO DE LOS JUEGOS PARA PRESTAMO
    public ArrayList<JuegoPrestamo> cargarInventarioPrestamo(ArrayList<JuegoDeMesa> juegos) throws IOException {
        ArrayList<JuegoPrestamo> inventario = new ArrayList<>();
        File f = new File(RUTA + "inventario_prestamo.txt");
        if (!f.exists()) return inventario;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            JuegoDeMesa juego = buscarJuego(p[0], juegos);
            if (juego != null) {
                inventario.add(new JuegoPrestamo(juego, Integer.parseInt(p[1]), Integer.parseInt(p[2])));
            }
        }
        br.close();
        return inventario;
    }

    // --- PERSISTENCIA DEL INVENTARIO VENTA ---
    
    //SE GUARDA EL INVENTARIO DE LOS JUEGOS A LA VENTA
    public void guardarInventarioVenta(Map<String, JuegoVenta> inventario) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "inventario_venta.txt"));
        for (Map.Entry<String, JuegoVenta> entry : inventario.entrySet()) {
            pw.println(entry.getKey() + SEP + entry.getValue().getPrecio() + SEP + entry.getValue().getStockVenta());
        }
        pw.close();
    }
    
    //SE CARGA EL INVENTARIO DE LOS JUEGOS A LA VENTA
    public Map<String, JuegoVenta> cargarInventarioVenta() throws IOException {
        Map<String, JuegoVenta> inventario = new HashMap<>();
        File f = new File(RUTA + "inventario_venta.txt");
        if (!f.exists()) return inventario;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            inventario.put(p[0], new JuegoVenta(Integer.parseInt(p[1]), Integer.parseInt(p[2])));
        }
        br.close();
        return inventario;
    }

    // --- PERSISTENCIA DE LOS CLIENTES ---

    //SE GUARDAN LOS CLIENTES
    public void guardarClientes(ArrayList<Cliente> clientes) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "clientes.txt"));
        for (Cliente c : clientes) {
            List<String> idsFavs = new ArrayList<>();
            for (JuegoDeMesa j : c.getJuegosFavoritos()) idsFavs.add(j.getIdJuego());
            String favs = String.join(SEP_LISTA, idsFavs);
            pw.println(c.getLogin() + SEP + c.getPassword() + SEP + c.getNombre() + SEP
                    + c.getIdCliente() + SEP + c.getPuntosFidelidad() + SEP + favs);
        }
        pw.close();
    }

    //SE CARGAN LOS CLIENTES
    public ArrayList<Cliente> cargarClientes(ArrayList<JuegoDeMesa> juegos) throws IOException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        File f = new File(RUTA + "clientes.txt");
        if (!f.exists()) return clientes;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            Cliente c = new Cliente(p[0], p[1], p[2], new ArrayList<>(), p[3], Integer.parseInt(p[4]));
            if (p.length > 5 && !p[5].isEmpty()) {
                for (String idJ : p[5].split(SEP_LISTA)) {
                    JuegoDeMesa j = buscarJuego(idJ, juegos);
                    if (j != null) c.agregarFavorito(j);
                }
            }
            clientes.add(c);
        }
        br.close();
        return clientes;
    }

    // --- PERSISTENCIA DE LOS MESEROS ---
    
    //SE GUARDAN LOS MESEROS
    public void guardarMeseros(ArrayList<Mesero> meseros) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "meseros.txt"));
        for (Mesero m : meseros) {
            List<String> idsFavs = new ArrayList<>();
            for (JuegoDeMesa j : m.getJuegosFavoritos()) idsFavs.add(j.getIdJuego());
            String favs = String.join(SEP_LISTA, idsFavs);
            List<String> idsConoce = new ArrayList<>();
            for (JuegoDeMesa j : m.getJuegosQueConoce()) idsConoce.add(j.getIdJuego());
            String conoce = String.join(SEP_LISTA, idsConoce);
            pw.println(m.getLogin() + SEP + m.getPassword() + SEP + m.getNombre() + SEP
                    + m.getIdEmpleado() + SEP + favs + SEP + conoce);
        }
        pw.close();
    }

    //SE CARGAN LOS MESEROS
    public ArrayList<Mesero> cargarMeseros(ArrayList<JuegoDeMesa> juegos) throws IOException {
        ArrayList<Mesero> meseros = new ArrayList<>();
        File f = new File(RUTA + "meseros.txt");
        if (!f.exists()) return meseros;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            ArrayList<JuegoDeMesa> favs = new ArrayList<>();
            if (p.length > 4 && !p[4].isEmpty()) {
                for (String idJ : p[4].split(SEP_LISTA)) {
                    JuegoDeMesa j = buscarJuego(idJ, juegos);
                    if (j != null) favs.add(j);
                }
            }
            ArrayList<JuegoDeMesa> conoce = new ArrayList<>();
            if (p.length > 5 && !p[5].isEmpty()) {
                for (String idJ : p[5].split(SEP_LISTA)) {
                    JuegoDeMesa j = buscarJuego(idJ, juegos);
                    if (j != null) conoce.add(j);
                }
            }
            meseros.add(new Mesero(p[0], p[1], p[2], favs, p[3], conoce));
        }
        br.close();
        return meseros;
    }

    // --- PERSISTENCIA DE LOS COCINEROS ---

    //SE GUARDAN LOS COCINEROS
    public void guardarCocineros(ArrayList<Cocinero> cocineros) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "cocineros.txt"));
        for (Cocinero c : cocineros) {
            List<String> idsFavs = new ArrayList<>();
            for (JuegoDeMesa j : c.getJuegosFavoritos()) idsFavs.add(j.getIdJuego());
            String favs = String.join(SEP_LISTA, idsFavs);
            pw.println(c.getLogin() + SEP + c.getPassword() + SEP + c.getNombre() + SEP
                    + c.getIdEmpleado() + SEP + favs);
        }
        pw.close();
    }

    //SE CARGAN LOS COCINEROS
    public ArrayList<Cocinero> cargarCocineros(ArrayList<JuegoDeMesa> juegos) throws IOException {
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        File f = new File(RUTA + "cocineros.txt");
        if (!f.exists()) return cocineros;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            ArrayList<JuegoDeMesa> favs = new ArrayList<>();
            if (p.length > 4 && !p[4].isEmpty()) {
                for (String idJ : p[4].split(SEP_LISTA)) {
                    JuegoDeMesa j = buscarJuego(idJ, juegos);
                    if (j != null) favs.add(j);
                }
            }
            cocineros.add(new Cocinero(p[0], p[1], p[2], favs, p[3]));
        }
        br.close();
        return cocineros;
    }

    // --- PERSISTENCIA DE ADMINISTRADORES ---

    //SE GUARDAN LOS ADMINISTRADORES
    public void guardarAdministradores(ArrayList<Administrador> administradores) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "administradores.txt"));
        for (Administrador a : administradores) {
            pw.println(a.getLogin() + SEP + a.getPassword() + SEP + a.getNombre());
        }
        pw.close();
    }

    //SE CARGAN LOS ADMINISTRADORES
    public ArrayList<Administrador> cargarAdministradores() throws IOException {
        ArrayList<Administrador> admins = new ArrayList<>();
        File f = new File(RUTA + "administradores.txt");
        if (!f.exists()) return admins;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            admins.add(new Administrador(p[0], p[1], p[2]));
        }
        br.close();
        return admins;
    }

    // --- PERSISTENCIA DE VENTAS ---
    
    //SE GUARDAN LAS VENTAS
    public void guardarVentas(ArrayList<Venta> ventas) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "ventas.txt"));
        PrintWriter pwD = new PrintWriter(new FileWriter(RUTA + "detalles_venta.txt"));
        for (Venta v : ventas) {
            pw.println(v.getIdVenta() + SEP + v.getFecha() + SEP + v.getRubro().name() + SEP
                    + v.getSubtotal() + SEP + v.getImpuestos() + SEP + v.getPropina() + SEP
                    + v.getTotal() + SEP + v.getPuntosGanados() + SEP + v.getPuntosRedimidos());
            for (DetalleVenta d : v.getDetallesVenta()) {
                pwD.println(v.getIdVenta() + SEP + d.getIdDetalle() + SEP + d.getCantidad() + SEP
                        + d.getPrecioUnitario() + SEP + d.getSubtotalLinea());
            }
        }
        pw.close();
        pwD.close();
    }

    //SE CARGAN LAS VENTAS
    public ArrayList<Venta> cargarVentas() throws IOException {
        Map<String, ArrayList<DetalleVenta>> detallesPorVenta = new HashMap<>();
        File fd = new File(RUTA + "detalles_venta.txt");
        if (fd.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(fd));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(",");
                DetalleVenta d = new DetalleVenta(p[1], Integer.parseInt(p[2]), Double.parseDouble(p[3]));
                d.setSubtotalLinea(Double.parseDouble(p[4]));
                if (!detallesPorVenta.containsKey(p[0])) {
                    detallesPorVenta.put(p[0], new ArrayList<>());
                }
                detallesPorVenta.get(p[0]).add(d);
            }
            br.close();
        }

        ArrayList<Venta> ventas = new ArrayList<>();
        File f = new File(RUTA + "ventas.txt");
        if (!f.exists()) return ventas;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            Venta v = new Venta(p[0], p[1], RubroVenta.valueOf(p[2]), Double.parseDouble(p[5]), Integer.parseInt(p[8]), null);
            v.setSubtotal(Double.parseDouble(p[3]));
            v.setImpuestos(Double.parseDouble(p[4]));
            v.setTotal(Double.parseDouble(p[6]));
            v.setPuntosGanados(Integer.parseInt(p[7]));
            v.setDetallesVenta(detallesPorVenta.getOrDefault(p[0], new ArrayList<>()));
            ventas.add(v);
        }
        br.close();
        return ventas;
    }

    // --- PERSISTENCIA DE LOS PRESTAMOS DE LOS JUEGOS ---

    //SE GUARDAN LOS PRESTAMOS
    public void guardarPrestamos(ArrayList<Prestamo> prestamos) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "prestamos.txt"));
        PrintWriter pwJ = new PrintWriter(new FileWriter(RUTA + "prestamos_juegos.txt"));
        for (Prestamo pr : prestamos) {
            pw.println(pr.getIdPrestamo() + SEP + pr.getEstadoPrestamo().name() + SEP
                    + pr.getFechaInicio() + SEP + pr.getFechaDevolucion() + SEP + pr.isAdvertenciaReglas());
            for (JuegoPrestamo jp : pr.getJuegos()) {
                pwJ.println(pr.getIdPrestamo() + SEP + jp.getJuego().getIdJuego() + SEP
                        + jp.getCopiasDisponibles() + SEP + jp.getCopiasPrestadas());
            }
        }
        pw.close();
        pwJ.close();
    }

    //SE CARGAN LOS PRESTAMOS
    public ArrayList<Prestamo> cargarPrestamos(ArrayList<JuegoDeMesa> juegos) throws IOException {
        Map<String, ArrayList<JuegoPrestamo>> juegosPorPrestamo = new HashMap<>();
        File fj = new File(RUTA + "prestamos_juegos.txt");
        if (fj.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(fj));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(",");
                JuegoDeMesa juego = buscarJuego(p[1], juegos);
                if (juego != null) {
                    JuegoPrestamo jp = new JuegoPrestamo(juego, Integer.parseInt(p[2]), Integer.parseInt(p[3]));
                    if (!juegosPorPrestamo.containsKey(p[0])) {
                        juegosPorPrestamo.put(p[0], new ArrayList<>());
                    }
                    juegosPorPrestamo.get(p[0]).add(jp);
                }
            }
            br.close();
        }

        ArrayList<Prestamo> prestamos = new ArrayList<>();
        File f = new File(RUTA + "prestamos.txt");
        if (!f.exists()) return prestamos;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            Prestamo pr = new Prestamo(p[0], p[2], null, null);
            pr.setEstadoPrestamo(EstadoPrestamo.valueOf(p[1]));
            pr.setFechaDevolucion(p[3]);
            pr.setAdvertenciaReglas(Boolean.parseBoolean(p[4]));
            pr.setJuegos(juegosPorPrestamo.getOrDefault(p[0], new ArrayList<>()));
            prestamos.add(pr);
        }
        br.close();
        return prestamos;
    }

    // --- PERSISTENCIA DE LAS MESAS ---

    //SE GUARDAN LAS MESAS
    public void guardarMesas(ArrayList<Mesa> mesas) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "mesas.txt"));
        for (Mesa m : mesas) {
            pw.println(m.getIdMesa() + SEP + m.isDisponible() + SEP + m.getCantidadPersonas() + SEP
                    + m.isHayNinios() + SEP + m.isHayJovenes());
        }
        pw.close();
    }

    //SE CARGAN LAS MESAS
    public ArrayList<Mesa> cargarMesas() throws IOException {
        ArrayList<Mesa> mesas = new ArrayList<>();
        File f = new File(RUTA + "mesas.txt");
        if (!f.exists()) return mesas;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            mesas.add(new Mesa(p[0], Boolean.parseBoolean(p[1]), Integer.parseInt(p[2]),
                    Boolean.parseBoolean(p[3]), Boolean.parseBoolean(p[4])));
        }
        br.close();
        return mesas;
    }

    // --- PERSISTENCIA DE LOS PLATILLOS ---

    //SE GUARDAN LOS PLATILLOS
    public void guardarPlatillos(ArrayList<Platillo> platillos) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "platillos.txt"));
        for (Platillo p : platillos) {
            if (p instanceof Bebida) {
                Bebida b = (Bebida) p;
                pw.println("BEBIDA" + SEP + b.getIdPlatillo() + SEP + b.getNombre() + SEP
                        + b.getPrecio() + SEP + b.isAlcoholica() + SEP + b.isCaliente());
            } else if (p instanceof Pasteleria) {
                Pasteleria pa = (Pasteleria) p;
                String alergenos = String.join(SEP_LISTA, pa.getPosiblesAlergenos());
                pw.println("PASTELERIA" + SEP + pa.getIdPlatillo() + SEP + pa.getNombre() + SEP
                        + pa.getPrecio() + SEP + alergenos);
            }
        }
        pw.close();
    }

    //SE CARGAN LOS PLATILLOS
    public ArrayList<Platillo> cargarPlatillos() throws IOException {
        ArrayList<Platillo> platillos = new ArrayList<>();
        File f = new File(RUTA + "platillos.txt");
        if (!f.exists()) return platillos;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            if (p[0].equals("BEBIDA")) {
                platillos.add(new Bebida(p[1], p[2], Integer.parseInt(p[3]),
                        Boolean.parseBoolean(p[4]), Boolean.parseBoolean(p[5])));
            } else if (p[0].equals("PASTELERIA")) {
                ArrayList<String> alergenos = new ArrayList<>();
                if (p.length > 4 && !p[4].isEmpty()) {
                    alergenos.addAll(Arrays.asList(p[4].split(SEP_LISTA)));
                }
                platillos.add(new Pasteleria(p[1], p[2], Integer.parseInt(p[3]), alergenos));
            }
        }
        br.close();
        return platillos;
    }

    // --- PERSISTENCIA DE LOS TURNOS ---

    //SE GUARDAN LOS TURNOS
    public void guardarTurnos(ArrayList<Turno> turnos) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "turnos.txt"));
        for (Turno t : turnos) {
            pw.println(t.getIdTurno() + SEP + t.getDiaSemana().name() + SEP + t.getHoraInicio() + SEP + t.getHoraFin());
        }
        pw.close();
    }

    //SE CARGAN LOS TURNOS
    public ArrayList<Turno> cargarTurnos() throws IOException {
        ArrayList<Turno> turnos = new ArrayList<>();
        File f = new File(RUTA + "turnos.txt");
        if (!f.exists()) return turnos;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            turnos.add(new Turno(p[0], DiaSemana.valueOf(p[1]), p[2], p[3]));
        }
        br.close();
        return turnos;
    }

    // --- PERSISTENCIA DE LAS RESERVAS ---

    //SE GUARDAN LAS RESERVAS
    public void guardarReservas(ArrayList<Reserva> reservas) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "reservas.txt"));
        for (Reserva r : reservas) {
            pw.println(r.getIdReserva() + SEP + r.getEstadoReserva().name() + SEP + r.getFecha());
        }
        pw.close();
    }

    //SE CARGAN LAS RESERVAS
    public ArrayList<Reserva> cargarReservas() throws IOException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        File f = new File(RUTA + "reservas.txt");
        if (!f.exists()) return reservas;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            reservas.add(new Reserva(p[0], EstadoReserva.valueOf(p[1]), p[2], null, null));
        }
        br.close();
        return reservas;
    }

    // --- PERSISTENCIA DE LAS SOLICITUDES DE CAMBIO DE TURNO ---

    //SE GUARDAN LAS SOLICITUDES DE CAMBIO DE TURNO
    public void guardarSolicitudes(ArrayList<SolicitudCambioTurno> solicitudes) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "solicitudes.txt"));
        for (SolicitudCambioTurno s : solicitudes) {
            pw.println(s.getIdSolicitud() + SEP + s.getEstado().name() + SEP + s.getTipoSolicitud().name());
        }
        pw.close();
    }

    //SE CARGAN LAS SOLICITUDES DE CAMBIO DE TURNO
    public ArrayList<SolicitudCambioTurno> cargarSolicitudes() throws IOException {
        ArrayList<SolicitudCambioTurno> solicitudes = new ArrayList<>();
        File f = new File(RUTA + "solicitudes.txt");
        if (!f.exists()) return solicitudes;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            SolicitudCambioTurno s = new SolicitudCambioTurno(p[0], TipoSolicitud.valueOf(p[2]));
            s.setEstado(EstadoSolicitud.valueOf(p[1]));
            solicitudes.add(s);
        }
        br.close();
        return solicitudes;
    }

    // --- PERSISTENCIA DE LAS SUGERENCIAS DE PLATILLO ---

    //SE GUARDAN LAS SUGERENCIAS DE PLATILLO
    public void guardarSugerencias(ArrayList<SugerenciaPlatillo> sugerencias) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "sugerencias.txt"));
        for (SugerenciaPlatillo s : sugerencias) {
            pw.println(s.getIdSugerencia() + SEP + s.getNombrePlatillo() + SEP
                    + s.getDescripcion() + SEP + s.getEstado().name());
        }
        pw.close();
    }

    //SE CARGAN LAS SUGERENCIAS DE PLATILLO
    public ArrayList<SugerenciaPlatillo> cargarSugerencias() throws IOException {
        ArrayList<SugerenciaPlatillo> sugerencias = new ArrayList<>();
        File f = new File(RUTA + "sugerencias.txt");
        if (!f.exists()) return sugerencias;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            String[] p = linea.split(",");
            SugerenciaPlatillo s = new SugerenciaPlatillo(p[0], p[1], p[2]);
            s.setEstado(EstadoSugerencia.valueOf(p[3]));
            sugerencias.add(s);
        }
        br.close();
        return sugerencias;
    }

    // --- PERSISTENCIA DEL CAFE ---

    //SE GUARDA LA INFORMACION DEL CAFE
    public void guardarCafe(Cafe cafe) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(RUTA + "cafe.txt"));
        pw.println(cafe.getNombre() + SEP + cafe.getDireccion() + SEP + cafe.getCapacidadMaxima());
        pw.close();
    }

    //SE CARGA LA INFORMACION DEL CAFE
    public Cafe cargarCafe() throws IOException {
        File f = new File(RUTA + "cafe.txt");
        if (!f.exists()) return null;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String linea = br.readLine();
        br.close();
        if (linea == null || linea.trim().isEmpty()) return null;
        String[] p = linea.split(",");
        return new Cafe(p[0], p[1], Integer.parseInt(p[2]));
    }
}
