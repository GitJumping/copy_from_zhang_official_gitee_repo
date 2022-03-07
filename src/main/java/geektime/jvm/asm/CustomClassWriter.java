package geektime.jvm.asm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.util.TraceClassVisitor;

public class CustomClassWriter {

    ClassReader reader;
    ClassWriter writer;

    PublicizeMethodAdapter pubMethAdapter;
    final static String CLASSNAME = "java.lang.Integer";
    final static String CLONEABLE = "java/lang/Cloneable";

    public CustomClassWriter() {

        try {
            reader = new ClassReader(CLASSNAME);
            writer = new ClassWriter(reader, 0);

        } catch (IOException ex) {
            Logger.getLogger(CustomClassWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CustomClassWriter(byte[] contents) {
        reader = new ClassReader(contents);
        writer = new ClassWriter(reader, 0);
    }

    public static void main(String[] args) {
        CustomClassWriter ccw = new CustomClassWriter();
        byte[] arrayByte = ccw.publicizeMethod();
    }

    public byte[] publicizeMethod() {
        pubMethAdapter = new PublicizeMethodAdapter(writer);
        reader.accept(pubMethAdapter, 0);
        return writer.toByteArray();
    }

    public class PublicizeMethodAdapter extends ClassVisitor {

        final Logger logger = Logger.getLogger("PublicizeMethodAdapter");
        TraceClassVisitor tracer;
        PrintWriter pw = new PrintWriter(System.out);

        public PublicizeMethodAdapter(ClassVisitor cv) {
            super(ASM4, cv);
            this.cv = cv;
            tracer = new TraceClassVisitor(cv, pw);
        }

        @Override
        public MethodVisitor visitMethod(int access,
                String name,
                String desc,
                String signature,
                String[] exceptions) {

            if (name.equals("toUnsignedString0")) {
                logger.info("Visiting unsigned method");
                return tracer.visitMethod(ACC_PUBLIC + ACC_STATIC, name, desc, signature, exceptions);
            }
            return tracer.visitMethod(access, name, desc, signature, exceptions);
        }

        public void visitEnd() {
            tracer.visitEnd();
            System.out.println(tracer.p.getText());
        }

    }
}