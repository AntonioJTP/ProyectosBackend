package test;

import model.Persona;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
  public static void obtenerNull (Persona persona) {
    Optional<String> optNombre = Optional.ofNullable(persona.getNombre());
    Optional<String> optPoblacion = Optional.ofNullable(persona.getPoblacion());
    Optional<Integer> optEdad = Optional.ofNullable(persona.getEdad());

    if (optNombre.isEmpty()) persona.setNombre("Desconocido");
    if (optPoblacion.isEmpty()) persona.setPoblacion("Desconocido");
    if (optEdad.isEmpty()) persona.setEdad(0);
  }

  public static void main(String[] args) {
    File text = new File("src/files/text.txt");
    Scanner sc = null;
    List<Persona> people = new ArrayList();
    AtomicInteger cont = new AtomicInteger(0);

    try {
      sc = new Scanner(text);
    } catch (FileNotFoundException e) {
      System.err.println("Archivo no encontrado");
    }

    while (sc.hasNextLine()) {
      String mainLine = sc.nextLine();

      Scanner lineSc = new Scanner(mainLine);
      lineSc.useDelimiter(":");

      while (lineSc.hasNext()) {
        Persona persona = new Persona(null, null, null);

        List<String> lines = new ArrayList();

        String line = lineSc.next();
        lines.add(line);
        if (line.matches("^$"))
          persona.setNombre(null);
        else
          persona.setNombre(line);

        line = lineSc.next();
        lines.add(line);
        if (line.matches("^$"))
          persona.setPoblacion(null);
        else
          persona.setPoblacion(line);

        try {
          line = lineSc.next();
          lines.add(line);
        } catch (NoSuchElementException e) {
          lines.add(null);
        }

        if (line.matches("[0-9]+")) {
          persona.setEdad(Integer.parseInt(line));
        } else {
          persona.setEdad(null);
        }
        obtenerNull(persona);
        people.add(persona);

        lines.clear();
      }
    }
    people.stream()
        .filter(key -> key.getEdad() != 0 && key.getEdad() < 25)
        .forEach(key -> System.out.printf("Linea %d: %s\n", cont.getAndIncrement(), key));
  }
}
