package com.rover12421.shaka.smali.baksmali;

import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.smali.util.NIOIndentingWriter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.baksmali.Adaptors.ClassDefinition;
import org.jf.baksmali.Adaptors.CommentingIndentingWriter;
import org.jf.baksmali.Adaptors.MethodDefinition;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.util.ReferenceUtil;
import org.jf.util.IndentingWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rover12421 on 11/9/15.
 */
@Aspect
public class ClassDefinitionAj {
    @Around("execution(* org.jf.baksmali.Adaptors.ClassDefinition.writeDirectMethods(..))" +
            "&& args(writer)")
    public Set<String> writeDirectMethods(ProceedingJoinPoint joinPoint, IndentingWriter writer) throws IOException {
        ClassDefinition thiz = (ClassDefinition) joinPoint.getThis();

        boolean wroteHeader = false;
        Set<String> writtenMethods = new HashSet<>();

        Iterable<? extends Method> directMethods;
        if (thiz.classDef instanceof DexBackedClassDef) {
            directMethods = ((DexBackedClassDef)thiz.classDef).getDirectMethods(false);
        } else {
            directMethods = thiz.classDef.getDirectMethods();
        }

        NIOIndentingWriter nioIndentingWriter = null;
        long point = -1;
        if (writer instanceof NIOIndentingWriter) {
            nioIndentingWriter = (NIOIndentingWriter) writer;
        }

        for (Method method: directMethods) {
            if (!wroteHeader) {
                writer.write("\n\n");
                writer.write("# direct methods");
                wroteHeader = true;
            }
            writer.write('\n');

            // TODO: check for method validation errors
            String methodString = ReferenceUtil.getMethodDescriptor(method, true);

            try {
                IndentingWriter methodWriter = writer;

                if (nioIndentingWriter != null) {
                    point = nioIndentingWriter.getPosition();
                }

                if (!writtenMethods.add(methodString)) {
                    writer.write("# duplicate method ignored\n");
                    methodWriter = new CommentingIndentingWriter(writer);
                }

                MethodImplementation methodImpl = method.getImplementation();
                if (methodImpl == null) {
                    MethodDefinition.writeEmptyMethodTo(methodWriter, method, thiz.options);
                } else {
                    MethodDefinition methodDefinition = new MethodDefinition(thiz, method, methodImpl);
                    methodDefinition.writeTo(methodWriter);
                }
            } catch (Throwable e) {
                String str = "# write writeDirectMethods error : " + methodString + "\n";
                LogHelper.fine(str);
                if (nioIndentingWriter != null) {
                    nioIndentingWriter.position(point);
                }
                writer.write(str);
            }
        }
        return writtenMethods;
    }

    @Around("execution(* org.jf.baksmali.Adaptors.ClassDefinition.writeVirtualMethods(..))" +
            "&& args(writer, directMethods)")
    public void writeVirtualMethods(ProceedingJoinPoint joinPoint, IndentingWriter writer, Set<String> directMethods) throws IOException {
        ClassDefinition thiz = (ClassDefinition) joinPoint.getThis();

        boolean wroteHeader = false;
        Set<String> writtenMethods = new HashSet<>();

        Iterable<? extends Method> virtualMethods;
        if (thiz.classDef instanceof DexBackedClassDef) {
            virtualMethods = ((DexBackedClassDef)thiz.classDef).getVirtualMethods(false);
        } else {
            virtualMethods = thiz.classDef.getVirtualMethods();
        }

        NIOIndentingWriter nioIndentingWriter = null;
        long point = -1;
        if (writer instanceof NIOIndentingWriter) {
            nioIndentingWriter = (NIOIndentingWriter) writer;
        }

        for (Method method: virtualMethods) {
            if (!wroteHeader) {
                writer.write("\n\n");
                writer.write("# virtual methods");
                wroteHeader = true;
            }
            writer.write('\n');

            // TODO: check for method validation errors
            String methodString = ReferenceUtil.getMethodDescriptor(method, true);

            try {
                IndentingWriter methodWriter = writer;

                if (nioIndentingWriter != null) {
                    point = nioIndentingWriter.getPosition();
                }

                if (!writtenMethods.add(methodString)) {
                    writer.write("# duplicate method ignored\n");
                    methodWriter = new CommentingIndentingWriter(writer);
                } else if (directMethods.contains(methodString)) {
                    writer.write("# There is both a direct and virtual method with this signature.\n" +
                            "# You will need to rename one of these methods, including all references.\n");
                    System.err.println(String.format("Duplicate direct+virtual method found: %s->%s",
                            thiz.classDef.getType(), methodString));
                    System.err.println("You will need to rename one of these methods, including all references.");
                }

                MethodImplementation methodImpl = method.getImplementation();
                if (methodImpl == null) {
                    MethodDefinition.writeEmptyMethodTo(methodWriter, method, thiz.options);
                } else {
                    MethodDefinition methodDefinition = new MethodDefinition(thiz, method, methodImpl);
                    methodDefinition.writeTo(methodWriter);
                }
            } catch (Throwable e) {
                String str = "# write writeVirtualMethods error : " + methodString + "\n";
                LogHelper.fine(str);
                if (nioIndentingWriter != null) {
                    nioIndentingWriter.position(point);
                }
                writer.write(str);
            }
        }
    }
}
