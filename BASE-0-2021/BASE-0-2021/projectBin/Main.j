.class public Main
.super java/lang/Object
;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 10 
       .limit stack 256

	; setup local variables:

       ;    1 - the PrintStream object held in java.lang.System.out
       getstatic java/lang/System/out Ljava/io/PrintStream;

       ; place your bytecodes here between START and END
       ; START

	aconst_null
	astore_3
	getstatic java/lang/System/out Ljava/io/PrintStream;
	new frame_0
	dup
	invokespecial frame_0/<init>()V
	dup
	aload_3
	putfield frame_0/sl Ljava/lang/Object;
	astore_3
	aload_3
	new ref_I
	dup
	invokespecial ref_I/<init>()V
	dup
	sipush 1
	putfield ref_I/v I
	putfield frame_0/result Ljava/lang/Object;
	aload_3
	sipush 1
	putfield frame_0/constant1 I
	aload_3
	sipush 2
	putfield frame_0/constant2 I
	pop
	aload_3
	getfield frame_0/constant2 I
	aload_3
	getfield frame_0/constant1 I
	isub
	ifgt L1
	sipush 0
	goto L2
	L1:
 sipush 1
	L2:
	ifeq L3
	aload_3
	getfield frame_0/result Ljava/lang/Object;
	checkcast ref_I
	sipush 1
	aload_3
	getfield frame_0/result Ljava/lang/Object;
	checkcast ref_I
	getfield ref_I/v I
	isub
	putfield ref_I/v I
	goto L4
	L3:
	aload_3
	getfield frame_0/result Ljava/lang/Object;
	checkcast ref_I
	aload_3
	getfield frame_0/result Ljava/lang/Object;
	checkcast ref_I
	getfield ref_I/v I
	putfield ref_I/v I
	L4:
	aload_3
	getfield frame_0/result Ljava/lang/Object;
	checkcast ref_I
	getfield ref_I/v I
	aload_3
	getfield frame_0/sl Ljava/lang/Object;
	astore_3
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
       
          
       ; END

    return
.end method

