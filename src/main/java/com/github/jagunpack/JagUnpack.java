package com.github.jagunpack;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Unpacker;
import java.util.zip.GZIPInputStream;

public class JagUnpack {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: jag-unpacker <dat0-file>");
            return;
        }

        Path src = Paths.get(args[0]);
        Path out = src.resolveSibling("runescape-unpacked.jar");

        if (Files.notExists(src)) {
            System.out.println("dat0 file not found");
            return;
        }

        byte[] packed = Files.readAllBytes(src);
        byte[] file = new byte[packed.length + 2];
        file[0] = 31;
        file[1] = -117;
        System.arraycopy(packed, 0, file, 2, packed.length);

        try (JarOutputStream jarOutputStream = new JarOutputStream(Files.newOutputStream(out))) {
            Unpacker unpacker = Pack200.newUnpacker();
            unpacker.unpack(new GZIPInputStream(new ByteArrayInputStream(file)), jarOutputStream);
        }
    }
}