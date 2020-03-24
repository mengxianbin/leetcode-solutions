package singleton;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class SingletonClassLoader extends ClassLoader {

    private String classpath;

    public SingletonClassLoader(String classpath) {
        super(null);
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classFilePath = getClassFilePath(name);
        byte[] bytes = getClassBytes(classFilePath);
        Class<?> klass = defineClass(null, bytes, 0, bytes.length);
        return klass != null ? klass : super.findClass(name);
    }

    private byte[] getClassBytes(String name) {
        try (FileInputStream inputStream = new FileInputStream(name);
             FileChannel inputChannel = inputStream.getChannel();

             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             WritableByteChannel outputChannel = Channels.newChannel(outputStream)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inputChannel.read(buffer) > 0) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getClassFilePath(String name) {
        return String.format("%s/%s.class", classpath, name.replace('.', '/'));
    }
}
