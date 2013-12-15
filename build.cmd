echo off
set currDIR=%CD%
call config.cmd
echo Limpiando...
rmdir /S /Q classes
rmdir /S /Q release
md release
md classes

echo Compilando...
javac source/*.java -d classes/

if errorlevel 1 goto theEnd

IF "%bindImages%"=="true" (
echo Generando Recursos...
  cd data\images\
  copy ..\..\tools\bindData\bindData.java
  javac bindData.java
  java bindData multiplicacion_facil.png n0.png n1.png n2.png n3.png n4.png n5.png n6.png n7.png n8.png n9.png About.png acercaDe.png AcercaDeTitle.png ayuda.png AyudaTitle.png continuar.png HLine.png menu.png menuTitle.png nuevo.png op_por.png Regresar.png SacercaDe.png salir.png Sayuda.png Scontinuar.png Snuevo.png Ssalir.png tAyuda.png vacio.png VLine.png
  del bindData.class
  move indice.java ..\..\source\
  cd..\..\
  copy data\images\data classes\
) ELSE (
  copy data\images\*.png classes\
)

echo Main-Class: Main>manif.inf
cd classes
jar cmf ../manif.inf jar.jar *

IF "%OBFUSCATE%"=="true" (
  cd ../tools
  echo Obfuscando...
  java -jar proguard.jar @obfuscate.pro
) ELSE (
 move jar.jar ..\release\MultiplicacionXCS.jar
)
cd..

del manif.inf
call run.cmd

:theEnd
rmdir /S /Q classes
if errorlevel 1 (
  rmdir /S /Q release
  pause
)

cd %currDIR%