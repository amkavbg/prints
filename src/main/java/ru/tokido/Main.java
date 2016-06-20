package ru.tokido;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tokido.engine.Engine;
import ru.tokido.engine.Printer;
import ru.tokido.engine.PrinterTemplate;
import ru.tokido.gui.MainWindow;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Engine engine = new Engine();

        EventQueue.invokeLater(() -> {
            //TODO: write here all recognize work
            System.out.println("GUI STARTED");
            new MainWindow();
        });

    }
}
