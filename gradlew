Êþº¾   2  !kotlin/collections/SetsKt__SetsKt  $kotlin/collections/SetsKt__SetsJVMKt  emptySet ()Ljava/util/Set; ,<T:Ljava/lang/Object;>()Ljava/util/Set<TT;>; #Lorg/jetbrains/annotations/NotNull; kotlin/collections/EmptySet 	 INSTANCE Lkotlin/collections/EmptySet;  	 
  java/util/Set  setOf $([Ljava/lang/Object;)Ljava/util/Set; 0<T:Ljava/lang/Object;>([TT;)Ljava/util/Set<TT;>; elements  kotlin/jvm/internal/Intrinsics  checkParameterIsNotNull '(Ljava/lang/Object;Ljava/lang/String;)V  
   kotlin/collections/ArraysKt  toSet  
   kotlin/collections/SetsKt !  
 " # [Ljava/lang/Object; Lkotlin/internal/InlineOnly;     
$i$f$setOf I mutableSetOf Lkotlin/SinceKotlin; version 1.1 java/util/LinkedHashSet . <init> ()V 0 1
 / 2 $i$f$mutableSetOf kotlin/collections/MapsKt 5 mapCapacity (I)I 7 8
 6 9 (I)V 0 ;
 / < java/util/Collection > toCollection A([Ljava/lang/Object;Ljava/util/Collection;)Ljava/util/Collection; @ A
  B 	hashSetOf ()Ljava/util/HashSet; 0<T:Ljava/lang/Object;>()Ljava/util/HashSet<TT;>; java/util/HashSet G
 H 2 $i$f$hashSetOf (([Ljava/lang/Object;)Ljava/util/HashSet; 4<T:Ljava/lang/Object;>([TT;)Ljava/util/HashSet<TT;>;
 H < linkedSetOf ()Ljava/util/LinkedHashSet; 6<T:Ljava/lang/Object;>()Ljava/util/LinkedHashSet<TT;>; $i$f$linkedSetOf .([Ljava/lang/Object;)Ljava/util/LinkedHashSet; :<T:Ljava/lang/Object;>([TT;)Ljava/util/LinkedHashSet<TT;>; orEmpty  (Ljava/util/Set;)Ljava/util/Set; A<T:Ljava/lang/Object;>(Ljava/util/Set<+TT;>;)Ljava/util/Set<TT;>; $Lorg/jetbrains/annotations/Nullable; $this$orEmpty Ljava/util/Set; $i$f$orEmpty optimizeReadOnlySet $this$optimizeReadOnlySet \ size ()I ^ _  ` iterator ()Ljava/util/Iterator; b c  d java/util/Iterator f next ()Ljava/lang/Object; h i g j #(Ljava/lang/Object;)Ljava/util/Set;  l
 " m Lkotlin/Metadata; mv       bv    k    xi d1ÞÀ0
À
"



À





#
ÀH0"ÀH0jH`"ÀHÂ5H0jH`"À2
H0"HÂ¢	H0
jH`"ÀHÂ5	H0
jH`"À2
H0"HÂ¢H0"ÀHÂ+H0"À2
H0"HÂ¢H0"ÀHÂ+H0"À2
H0"HÂ¢H0"À*H0HÀ!H0"À*
H0HÂÂ¨ d2   T Ljava/util/HashSet; Lkotlin/collections/HashSet; Ljava/util/LinkedHashSet; "Lkotlin/collections/LinkedHashSet; kotlin-stdlib xs Sets.kt
  2 Code LineNumberTable 	Signature RuntimeInvisibleAnnotations StackMapTable LocalVariableTable $RuntimeInvisibleParameterAnnotations 
SourceFile RuntimeVisibleAnnotations                       ² À °           "                     M     *¸ *¾ 
*¸  § ¸ $°        B        *          %                               1     ';¸ $°          1        ( )             &    *      8     ';» /Y· 3À °          ;        4 )             +  ,s - &    *      G     *¸ *» /Y*¾¸ :· =À ?¸ CÀ °          B          %                         D E     5     ';» HY· I°          G        J )       F      +  ,s - &    D K     G     *¸ *» HY*¾¸ :· MÀ ?¸ CÀ H°          J          %       L                  N O     5     ';» /Y· 3°          O        Q )       P      +  ,s - &    N R     G     *¸ *» /Y*¾¸ :· =À ?¸ CÀ /°          U          %       S                  T U     ^     '<*YÆ § W¸ $°        ÿ     C        Y         X Y     Z )      V      &        W    [ U          =*]¸ *¹ a ª      /             ¸ $§ *¹ e ¹ k ¸ n§ *°       	 $@        [ $ \ * ] ; ^ < [ < _        = \ Y       V                  0 1          *· ±              z  o  p[ I qI qI r s[ I qI 'I t uI v wI q x[ s y z[ s s {s |s Ds }s ~s s {s Ks Ns s s Rs *s {s s s [s Ts  s !                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      