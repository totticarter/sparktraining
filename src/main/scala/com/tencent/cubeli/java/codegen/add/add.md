# 参考链接
https://blog.csdn.net/aesop_wubo/article/details/48930913
https://blog.csdn.net/aesop_wubo/article/details/48948211

# 查看方法
javap -constants AddOper.class

'''
public interface com.tencent.cubeli.java.codegen.add.AddOper extends com.tencent.cubeli.java.codegen.add.Oper {
  public static final java.lang.String operateType = "+";
  public abstract int add(int, int);
}
'''

# 报错
## AddOperImplGenerator
Exception in thread "main" java.lang.IncompatibleClassChangeError: class org.objectweb.asm.util.TraceClassVisitor has interface org.objectweb.asm.ClassVisitor as super class
