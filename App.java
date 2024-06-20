//clase principal donde se implementa las funcionalidades de la aplicacion
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        GestionInventario inventario = new GestionInventario();
        int opc = -1;
        
        while(opc != 0) {
            try {
                String menu = "------------ Menú de Opciones -----------\n" +
                              "1. Agregar nuevo producto\n" +
                              "2. Actualizar cantidad de producto\n" +
                              "3. Buscar producto\n" +
                              "4. Eliminar Producto\n" +
                              "0. Salir\n" +
                              "Ingrese la opción que desea realizar:";
                opc = Integer.parseInt(JOptionPane.showInputDialog(menu));
                
                switch(opc) {
                    case 0: 
                        break;
                    case 1: 
                        String codAgregar = JOptionPane.showInputDialog("Ingrese el código del producto:");
                        int cantAgregar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto:"));
                        inventario.agregarProducto(codAgregar, cantAgregar);
                        JOptionPane.showMessageDialog(null, "Producto agregado.");
                        break;
                    case 2: 
                        String codActualizar = JOptionPane.showInputDialog("Ingrese el código del producto:");
                        int cantActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del producto:"));
                        inventario.actualizarCantidad(codActualizar, cantActualizar);
                        JOptionPane.showMessageDialog(null, "Cantidad actualizada.");
                        break;
                    case 3: 
                        String codBuscar = JOptionPane.showInputDialog("Ingrese el código del producto:");
                        Producto producto = inventario.buscarProducto(codBuscar);
                        if (producto != null) {
                            JOptionPane.showMessageDialog(null, "Producto encontrado: " + producto.getCodigo() + ", Cantidad: " + producto.getCantidad());
                        } else {
                            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                        }
                        break;
                    case 4: 
                        String codEliminar = JOptionPane.showInputDialog("Ingrese el código del producto:");
                        inventario.eliminarProducto(codEliminar);
                        JOptionPane.showMessageDialog(null, "Producto eliminado.");
                        break;
                    default: 
                        JOptionPane.showMessageDialog(null, "Opción no válida, inténtelo nuevamente.");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tipo de dato incorrecto. Inténtelo nuevamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al ingresar los datos. Inténtelo nuevamente.");
            }
        }
    }
}
