package com.tencent.cubeli.java.codegen.add;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.*;

/**
 * Created by liyong on 15/08/2018.
 */
public class AddOperImplGenerator {
    public static void main(String[] args) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        PrintWriter printWriter = new PrintWriter(System.out);
        TraceClassVisitor visitor = new TraceClassVisitor(cw, printWriter);

        visitor.visit(V1_5, ACC_PUBLIC, "com/tencent/cubeli/java/codegen/add/AddOperImpl", null, "java/lang/Object", new String[]{"com/tencent/cubeli/java/codegen/add/AddOper"});

        //添加构造方法
        MethodVisitor mv = visitor.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        // 添加add方法
        mv = visitor.visitMethod(ACC_PUBLIC, "add", "(II)I", null, null);
        mv.visitCode();
        mv.visitVarInsn(ILOAD, 1);
        mv.visitVarInsn(ILOAD, 2);
        mv.visitInsn(IADD);
        mv.visitInsn(IRETURN);
        mv.visitMaxs(2, 3);
        mv.visitEnd();

        visitor.visitEnd();

        FileOutputStream fos = new FileOutputStream(new File("/Users/liyong/software/sparktraining/asmclass/AddOperImpl.class"));
        fos.write(cw.toByteArray());
        fos.close();
    }
}
