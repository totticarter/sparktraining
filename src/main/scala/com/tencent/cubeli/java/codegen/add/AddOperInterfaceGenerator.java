package com.tencent.cubeli.java.codegen.add;


import org.objectweb.asm.ClassWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static org.objectweb.asm.Opcodes.*;

/**
 * Created by liyong on 15/08/2018.
 */
public class AddOperInterfaceGenerator {

    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "com/tencent/cubeli/java/codegen/add/AddOper", null, "java/lang/Object",
                new String[]{"com/tencent/cubeli/java/codegen/add/Oper"});
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "operateType", "Ljava/lang/String;",
                null, "+").visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "add",
                "(II)I", null, null).visitEnd();
        cw.visitEnd();
        FileOutputStream fos = new FileOutputStream(new File("/Users/liyong/software/sparktraining/asmclass/AddOper.class"));
        fos.write(cw.toByteArray());
        fos.close();
    }

}
