package hibernate.crud;
 
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Scanner;
import javax.swing.*;  
import hibernate.entity.EmpleadoEntity;
import hibernate.util.HibernateUtil;
 
public class MainCRUD {
 
	// Funci�n que guarda el registro del empleado
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
    
    // Funci�n que actualiza el registro del empleado
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
 
    // Funci�n que elimina el registro del empleado
    public void deleteEmpleado(EmpleadoEntity empleado) {
        Transaction t = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.delete(empleado);
            t.commit();
            System.out.println("Empleado eliminado con �xito.");
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }
    
    // Funci�n que recibe el registro del empleado
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
    	
        // Variables necesarias durante la ejecuci�n
        int repetidor = 1;
    	int crear = 0;
    	String nombre, apellido, direccion, telefono, cargo;
    	int edad;
    	
    	// Creaci�n de un objeto MainCRUD
        MainCRUD ejemplo = new MainCRUD();
        
        // Inicializaci�n de los Scanners para capturar datos
        Scanner s = new Scanner(System.in);
        Scanner st = new Scanner(System.in);
        
        // Inicializaci�n de JFrame para hacer uso de JOptionPane
        JFrame f = new JFrame();      
        
        // Impresi�n en consola del programa CRUD
            
        	// T�tulo del programa
    	System.out.println("\n------------------ CRUD HIBERNATE ------------------");
    	do {
    		
    		// Men� de opciones
    	int opcion;
    	System.out.println("\nMen� de operaciones de CRUD");
    	System.out.println("1. Crear registro");
    	System.out.println("2. Leer registros");
    	System.out.println("3. Actualizar registros");
    	System.out.println("4. Eliminar registros\n");
    	System.out.print("Escriba el n�mero de la opci�n: ");
    	opcion = s.nextInt();
    		
    		// Switch que maneja las opciones del men�
    	switch(opcion) {
    	case 1: 
    		// Opci�n 1: Creaci�n de registros (Crud)
    		System.out.println("\nCreaci�n de registros");
    		System.out.print("�Cu�ntos registros desea crear? Escribir cantidad: ");
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
        		System.out.print("Escriba la direcci�n del empleado: ");
        		direccion = st.nextLine();
        		System.out.print("Escriba el tel�fono del empleado: ");
        		telefono = st.nextLine();
        		
            	empleado.setNombreEmpleado(nombre);
                empleado.setApellidoEmpleado(apellido);
                empleado.setCargoEmpleado(cargo);
                empleado.setEdadEmpleado(edad);
                empleado.setDireccionEmpleado(direccion);
                empleado.setTelefonoEmpleado(telefono);
                
                ejemplo.saveEmpleado(empleado);
               
        		System.out.println("\nEmpleado registrado con �xito.\n");
        	}
    		break;
    	case 2: 
    		// Opci�n 2: Lectura de registros (cRud)
    		System.out.println("\nLectura de registros");
    		int lectura;
        	System.out.println("1. Registro �nico");
        	System.out.println("2. Todos los registros");
        	System.out.print("\nEscriba el n�mero de la opci�n: ");
        	lectura = s.nextInt();
        	
        	switch(lectura) {
        	case 1: 

                lectura = Integer.parseInt(JOptionPane.showInputDialog(f,"Escriba el ID del empleado a leer: "));
            	EmpleadoEntity nuevoEmpleado = ejemplo.fetchEmpleado(lectura);
            	System.out.println("\nEl empleado le�do de ID " + lectura + " es:\n" + nuevoEmpleado);
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
    		// Opci�n 3: Actualizaci�n de registros (crUd)
    		System.out.println("\nActualizaci�n de registros");
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
    		System.out.print("Escriba la direcci�n nueva del empleado: ");
    		direccion = st.nextLine();
    		System.out.print("Escriba el tel�fono nuevo del empleado: ");
    		telefono = st.nextLine();
    		
        	empleado.setNombreEmpleado(nombre);
            empleado.setApellidoEmpleado(apellido);
            empleado.setCargoEmpleado(cargo);
            empleado.setEdadEmpleado(edad);
            empleado.setDireccionEmpleado(direccion);
            empleado.setTelefonoEmpleado(telefono);
            
            ejemplo.updateEmpleado(empleado);
            System.out.println("\nEmpleado actualizado con �xito.\n");
        	System.out.println("\nEmpleado actualizado:\n" + empleado); 
        	}else {
                System.out.println("Empleado no encontrado.");
        	}
        	
    		break;
    	case 4: 
    		// Opci�n 4: Eliminaci�n de registros (cruD)
    		System.out.println("\nEliminaci�n de registros");
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
    		System.out.println("\nOpci�n incorrecta, intentelo de nuevo.");
    		break;
    	}
    	// Gestor de las repeticiones del men�
    	System.out.print("\n�Desea continuar? Escriba 1 (S�) o 0 (No): ");
    	repetidor = s.nextInt();    	
    	}while(repetidor!=0);
    	// Fin del programa
    	System.out.println("\n------------------ FIN DEL PROGRAMA ------------------");
    	
    	// Cierre de los Scanners
    	s.close();
    	st.close();
    }
}
