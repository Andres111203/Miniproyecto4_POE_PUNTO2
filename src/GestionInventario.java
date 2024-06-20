import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GestionInventario {
    private HashMap<String, Producto> inventario;

    public GestionInventario() {
        this.inventario = new HashMap<>();
        cargarInventario();
    }

    private void cargarInventario() {
        // Lógica para cargar el inventario desde el archivo (si es necesario)
        // Este método se puede usar para inicializar el inventario desde el archivo al iniciar el programa.
    }

    public void agregarProducto(String codigo, int cantidad) {
        if (inventario.containsKey(codigo)) {
            Producto producto = inventario.get(codigo);
            producto.setCantidad(producto.getCantidad() + cantidad);
        } else {
            inventario.put(codigo, new Producto(codigo, cantidad));
        }
        guardarInventario();
    }

    public void eliminarProducto(String codigo) {
        inventario.remove(codigo);
        guardarInventario();
    }

    public Producto buscarProducto(String codigo) {
        Producto producto = inventario.get(codigo);
        if (producto == null) {
            producto = buscarProductoEnArchivo(codigo);
        }
        return producto;
    }

    private Producto buscarProductoEnArchivo(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(codigo)) {
                    int cantidad = Integer.parseInt(data[1]);
                    Producto producto = new Producto(codigo, cantidad);
                    inventario.put(codigo, producto); 
                    return producto;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarCantidad(String codigo, int cantidad) {
        if (inventario.containsKey(codigo)) {
            Producto producto = inventario.get(codigo);
            producto.setCantidad(cantidad);
            guardarInventario();
        }
    }

    public void guardarInventario() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventario.txt"))) {
            for (Producto producto : inventario.values()) {
                writer.write(producto.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
