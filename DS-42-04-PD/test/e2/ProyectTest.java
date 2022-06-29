package e2;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProyectTest {


    //test para los metodos de Trabajador
    @Test
    void TrabajadorTest(){

        Trabajador t1 = new Trabajador("Juan", 3);
        Trabajador t2 = new Trabajador("Lucas", 2);
        Trabajador t3 = new Trabajador("Paula", 4);

        t1.setCosto(5); //cambiamos el coste de t1
        //comprobamos que el valor de los atributos es correcto
        assertEquals("Juan", t1.getNombre());
        assertEquals("Lucas", t2.getNombre());
        assertEquals("Paula", t3.getNombre());
        assertEquals(5, t1.getCosto());
        assertEquals(2, t2.getCosto());
        assertEquals(4, t3.getCosto());
        assertEquals(0, t1.getHora());
        assertEquals(0, t2.getHora());
        assertEquals(0, t3.getHora());

        //contabilizamos las horas de cada trabajador
        t1.finalizarJornada(t1, 100);
        t2.finalizarJornada(t2, 60);
        t3.finalizarJornada(t3, 70);

        //devolvemos las horas invertidas por cada trabajador
        assertEquals(100, t1.horasInvertidas());
        assertEquals(60, t2.horasInvertidas());
        assertEquals(70, t3.horasInvertidas());

        //calculamos el costo de cada trabajador
        assertEquals(500, t1.costeTotal());
        assertEquals(120, t2.costeTotal());
        assertEquals(280, t3.costeTotal());

        //Comprobamos el metodo print
        assertEquals("Worker Juan: 100 hours, 500.0€\n", t1.print(0));
        assertEquals("\tWorker Lucas: 60 hours, 120.0€\n", t2.print(1));
        assertEquals("\t\tWorker Paula: 70 hours, 280.0€\n", t3.print(2));

        //Comprobamos el metodo print_list
        assertEquals("Juan ", t1.print_list());
        assertEquals("Lucas ", t2.print_list());
        assertEquals("Paula ", t3.print_list());


        //si el trabajador coincide con el trabajador pasado por parametro
        assertTrue(t1.exists(t1));
        //si el trabajador no coincide con el trabajador pasado por parametro
        assertFalse(t1.exists(t2));

       //no se puede aplicar el metodo añadir y quitar sobre un trabajador
        assertThrows(UnsupportedOperationException.class, () -> t1.anadir(t2, t3));
        assertThrows(UnsupportedOperationException.class, () -> t2.quitar(t1, t3));

    }

    //test para los metodos de EquipoTrabajo
    @Test
    void EquipoTrabajoTest(){
        Trabajador t1 = new Trabajador("Juan", 3);
        Trabajador t2 = new Trabajador("Lucas", 2);
        Trabajador t3 = new Trabajador("Paula", 4);
        Trabajador t4 = new Trabajador("Tania", 5);
        EquipoTrabajo e1 = new EquipoTrabajo("Global");
        EquipoTrabajo e2 = new EquipoTrabajo("Subequipo");

        //comprobamos que el valor de los atributos es correcto
        assertEquals("Global", e1.getNombre());
        assertEquals("Subequipo", e2.getNombre());

        e1.insertar(t1);
        e2.insertar(t2);
        e2.insertar(t3);
        e2.insertar(t4);

        //comprobamos que se almacenaron de forma correcta
        assertEquals(t1, e1.getGrupoTrabajo().get(0));
        assertEquals(t2, e2.getGrupoTrabajo().get(0));
        assertEquals(t3, e2.getGrupoTrabajo().get(1));
        assertEquals(t4, e2.getGrupoTrabajo().get(2));
        //eliminamos el trabajador 2
        e2.eliminar(t2);
        assertEquals(t3, e2.getGrupoTrabajo().get(0));
        e2.insertar(t2);
        e1.insertar(e2);

        //contabilizamos las horas de cada trabajador
        t1.finalizarJornada(t1, 100);
        t2.finalizarJornada(t2, 60);
        t3.finalizarJornada(t3, 70);
        t4.finalizarJornada(t4, 50);


        //comprobamos que se calculan bien las horasInvertidas y el costeTotal
        assertEquals(180, e2.horasInvertidas());
        assertEquals(280, e1.horasInvertidas());
        assertEquals((60*2+70*4+50*5), e2.costeTotal());
        assertEquals((60*2+70*4+50*5+100*3), e1.costeTotal());



        //Comprobamos el metodo print
        String print_e2;
        print_e2 = "Team Subequipo: 180 hours, 650.0€\n" +
                "\tWorker Paula: 70 hours, 280.0€\n" +
                "\tWorker Tania: 50 hours, 250.0€\n" +
                "\tWorker Lucas: 60 hours, 120.0€\n";


        String print_e1;
        print_e1 = "Team Global: 280 hours, 950.0€\n" +
                "\tWorker Juan: 100 hours, 300.0€\n" +
                "\tTeam Subequipo: 180 hours, 650.0€\n" +
                "\t\tWorker Paula: 70 hours, 280.0€\n" +
                "\t\tWorker Tania: 50 hours, 250.0€\n" +
                "\t\tWorker Lucas: 60 hours, 120.0€\n";


        assertEquals(print_e2, e2.print(0));
        assertEquals(print_e1, e1.print(0));

        //Comprobamos metodo print_list

        assertEquals("Paula Tania Lucas ", e2.print_list());
        assertEquals("Juan Paula Tania Lucas ", e1.print_list());


        //comprobamos el funcionamiento del exists
        assertTrue(e2.exists(t3));
        assertTrue(e2.exists(t4));
        assertFalse(e2.exists(t1));
        assertTrue(e1.exists(e2));
        assertTrue(e1.exists(t2));

        Trabajador t5 = new Trabajador("Juan", 3);
        Trabajador t6 = new Trabajador("Lucas", 2);
        //insertamos los trabajadores
        e1.anadir(e1, t5);
        e2.anadir(e2, t6);
        assertEquals(t5, e1.getGrupoTrabajo().get(e1.getGrupoTrabajo().size()-1));
        assertEquals(t6, e2.getGrupoTrabajo().get(e2.getGrupoTrabajo().size()-1));
        //eliminamos los trabajadores
        e1.quitar(e1, t5);
        e2.quitar(e2, t6);
        assertNotEquals(t5, e1.getGrupoTrabajo().get(e1.getGrupoTrabajo().size()-1));
        assertNotEquals(t6, e2.getGrupoTrabajo().get(e2.getGrupoTrabajo().size()-1));


    }

    @Test
    void proyectoTest(){
        //inicializamos las instancias necesarias
        Trabajador t1 = new Trabajador("Juan", 3);
        Trabajador t2 = new Trabajador("Lucas", 2);
        Trabajador t3 = new Trabajador("Paula", 4);
        Trabajador t4 = new Trabajador("Tania", 5);
        Trabajador t5 = new Trabajador("Oscar", 7);
        EquipoTrabajo e = new EquipoTrabajo("Global");
        EquipoTrabajo e1 = new EquipoTrabajo("Subequipo1");
        EquipoTrabajo e2 = new EquipoTrabajo("Subequipo2");
        e1.insertar(t2);
        e1.insertar(t3);
        e1.insertar(t4);
        e2.insertar(t5);
        e.insertar(e1);
        e.insertar(t1);
        e.insertar(e2);



        //Instanciamos un proyecto
        Proyecto p = new Proyecto("Proyecrto 1", e);

        //comprobamos que el valor de los atributos es correcto
        assertEquals("Proyecrto 1", p.getName());
        assertEquals(e, p.getGrupo());

        Trabajador t6 = new Trabajador("Emilio", 4);
        p.anadir(e2, t6);
        p.quitar(e1, t4);

        p.finalizarJornada(t1, 100);
        p.finalizarJornada(t2, 60);
        p.finalizarJornada(t3, 70);
        p.finalizarJornada(t5, 50);
        p.finalizarJornada(t6, 80);

        //Comprobamos el print
        String print;
        print="Team Global: 360 hours, 1370.0€\n" +
                "\tTeam Subequipo1: 130 hours, 400.0€\n" +
                "\t\tWorker Lucas: 60 hours, 120.0€\n" +
                "\t\tWorker Paula: 70 hours, 280.0€\n" +
                "\tWorker Juan: 100 hours, 300.0€\n" +
                "\tTeam Subequipo2: 130 hours, 670.0€\n" +
                "\t\tWorker Oscar: 50 hours, 350.0€\n" +
                "\t\tWorker Emilio: 80 hours, 320.0€\n";

        assertEquals(print, p.print());

        //Comprobamos el print_list
        assertEquals("Lucas Paula Juan Oscar Emilio ", p.print_list(t2));
        assertThrows(UnsupportedOperationException.class, () -> p.print_list(t4)); //t4 no esta en el proyecto ya que la quitamos antes

        //Ejecutamos las funciones print() y print_list() para ver que funcionan correctamente
        System.out.println(p.print() + "\n");
        System.out.println(p.print_list(t2) + "\n");

    }


}
