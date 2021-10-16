package hibernate.crud;
 
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Scanner;
import javax.swing.*;  
import hibernate.entity.EmpleadoEntity;
import hibernate.util.HibernateUtil;
 
public class MainCRUD {
 
	// Función que guarda el registro del empleado
    public EmpleadoEntity saveEmpleado(EmpleadoEntity empleado) {
        Transaction t = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            Integer id = (Integer) session.save(empleado);
            empleado.setIdEmpleado(id);
            t.commit();
 
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return empleado;
    }
    
    // Función que actualiza el registro del empleado
    public void updateEmpleado(EmpleadoEntity empleado) {
        Transaction t = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.update(empleado);
            t.commit();
 
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }
 
    // Función que elimina el registro del empleado
    public void deleteEmpleado(EmpleadoEntity empleado) {
        Transaction t = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.delete(empleado);
            t.commit();
            System.out.println("Empleado eliminado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }
    
    // Función que recibe el registro del empleado
    public EmpleadoEntity fetchEmpleado(Integer idEmpleado) {
        Session session = null;
        EmpleadoEntity entity =  null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entity = (EmpleadoEntity) session.get(EmpleadoEntity.class, idEmpleado);
             
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Empleado no encontrado.");
        } finally {
            if (session != null)
                session.close();
        }
        return entity;
    }
    
    // Main de nuestro programa CRUD
    public static void main(String[] args) {
    	
    	// Gestor de notificaciones
    	@SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF); 
    	
        // Variables necesarias durante la ejecución
        int repetidor = 1;
    	int crear = 0;
    	String nombre, apellido, direccion, telefono, cargo;
    	int edad;
    	
    	// Creación de un objeto MainCRUD
        MainCRUD ejemplo = new MainCRUD();
        
        // Inicialización de los Scanners para capturar datos
        Scanner s = new Scanner(System.in);
        Scanner st = new Scanner(System.in);
        
        // Inicialización de JFrame para hacer uso de JOptionPane
        JFrame f = new JFrame();      
        
        // Impresión en consola del programa CRUD
            
        	// Título del programa
    	System.out.println("\n------------------ CRUD HIBERNATE ------------------");
    	do {
    		
    		// Menú de opciones
    	int opcion;
    	System.out.println("\nMenú de operaciones de CRUD");
    	System.out.println("1. Crear registro");
    	System.out.println("2. Leer registros");
    	System.out.println("3. Actualizar registros");
    	System.out.println("4. Eliminar registros\n");
    	System.out.print("Escriba el número de la opción: ");
    	opcion = s.nextInt();
    		
    		// Switch que maneja las opciones del menú
    	switch(opcion) {
    	case 1: 
    		// Opción 1: Creación de registros (Crud)
    		System.out.println("\nCreación de registros");
    		System.out.print("¿Cuántos registros desea crear? Escribir cantidad: ");
        	crear = s.nextInt();
        	for (int i=0; i<crear; i++) {
                EmpleadoEntity empleado = new EmpleadoEntity();
        		System.out.print("Escriba el nombre del empleado: ");
        		nombre = st.nextLine();
        		System.out.print("Escriba el apellido del empleado: ");
        		apellido = st.nextLine();
        		System.out.print("Escriba el cargo del empleado: ");
        		cargo = st.nextLine();
        		System.out.print("Escriba la edad del empleado: ");
        		edad = s.nextInt();
        		System.out.print("Escriba la dirección del empleado: ");
        		direccion = st.nextLine();
        		System.out.print("Escriba el teléfono del empleado: ");
        		telefono = st.nextLine();
        		
            	empleado.setNombreEmpleado(nombre);
                empleado.setApellidoEmpleado(apellido);
                empleado.setCargoEmpleado(cargo);
                empleado.setEdadEmpleado(edad);
                empleado.setDireccionEmpleado(direccion);
                empleado.setTelefonoEmpleado(telefono);
                
                ejemplo.saveEmpleado(empleado);
               
        		System.out.println("\nEmpleado registrado con éxito.\n");
        	}
    		break;
    	case 2: 
    		// Opción 2: Lectura de registros (cRud)
    		System.out.println("\nLectura de registros");
    		int lectura;
        	System.out.println("1. Registro único");
        	System.out.println("2. Todos los registros");
        	System.out.print("\nEscriba el número de la opción: ");
        	lectura = s.nextInt();
        	
        	switch(lectura) {
        	case 1: 

                lectura = Integer.parseInt(JOptionPane.showInputDialog(f,"Escriba el ID del empleado a leer: "));
            	EmpleadoEntity nuevoEmpleado = ejemplo.fetchEmpleado(lectura);
            	System.out.println("\nEl empleado leído de ID " + lectura + " es:\n" + nuevoEmpleado);
        		break;
        	case 2:
            	for (int i=1; i<300; i++) {
            		EmpleadoEntity nEmpleado = ejemplo.fetchEmpleado(i);
            		if(nEmpleado!=null) {
                    	System.out.println("" + nEmpleado);
            		}
            	}
        		break;
        	}
    		break;
    	case 3: 
    		// Opción 3: Actualización de registros (crUd)
    		System.out.println("\nActualización de registros");
    		int mod;
        	mod = Integer.parseInt(JOptionPane.showInputDialog(f, "Escriba el ID del empleado a actualizar: "));
        	EmpleadoEntity empleado = ejemplo.fetchEmpleado(mod);
        	
        	if (empleado!=null) {
        	System.out.println("\nEmpleado a actualizar:\n" + empleado + "\n"); 
    		System.out.print("Escriba el nombre nuevo del empleado: ");
    		nombre = st.nextLine();
    		System.out.print("Escriba el apellido nuevo del empleado: ");
    		apellido = st.nextLine();
    		System.out.print("Escriba el cargo nuevo del empleado: ");
    		cargo = st.nextLine();
    		System.out.print("Escriba la edad nueva del empleado: ");
    		edad = s.nextInt();
    		System.out.print("Escriba la dirección nueva del empleado: ");
    		direccion = st.nextLine();
    		System.out.print("Escriba el teléfono nuevo del empleado: ");
    		telefono = st.nextLine();
    		
        	empleado.setNombreEmpleado(nombre);
            empleado.setApellidoEmpleado(apellido);
            empleado.setCargoEmpleado(cargo);
            empleado.setEdadEmpleado(edad);
            empleado.setDireccionEmpleado(direccion);
            empleado.setTelefonoEmpleado(telefono);
            
            ejemplo.updateEmpleado(empleado);
            System.out.println("\nEmpleado actualizado con éxito.\n");
        	System.out.println("\nEmpleado actualizado:\n" + empleado); 
        	}else {
                System.out.println("Empleado no encontrado.");
        	}
        	
    		break;
    	case 4: 
    		// Opción 4: Eliminación de registros (cruD)
    		System.out.println("\nEliminación de registros");
    		int elimina;
        	elimina = Integer.parseInt(JOptionPane.showInputDialog(f, "Escriba el ID del empleado a eliminar: "));
        	
        	EmpleadoEntity eEmpleado = ejemplo.fetchEmpleado(elimina);
        	
        	if (eEmpleado!=null) {
        	System.out.println("\nEmpleado a eliminar:\n" + eEmpleado + "\n"); 
            ejemplo.deleteEmpleado(eEmpleado);
        	}else {
                System.out.println("Empleado no encontrado.");
        	}
    		break;
    	default: 
    		System.out.println("\nOpción incorrecta, intentelo de nuevo.");
    		break;
    	}
    	// Gestor de las repeticiones del menú
    	System.out.print("\n¿Desea continuar? Escriba 1 (Sí) o 0 (No): ");
    	repetidor = s.nextInt();    	
    	}while(repetidor!=0);
    	// Fin del programa
    	System.out.println("\n------------------ FIN DEL PROGRAMA ------------------");
    	
    	// Cierre de los Scanners
    	s.close();
    	st.close();
    }
}
