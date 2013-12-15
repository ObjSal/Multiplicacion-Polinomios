rem echo off
echo Limpiando...
rm -dr classes
rm -dr release
mkdir release
mkdir classes
cp data/images/*.png classes/
echo Compilando...
javac source/*.java -d classes/
echo Main-Class: Main>manif.inf
cd classes
jar cmf ../manif.inf jar.jar *
cd ../tools
echo Obfuscando...
java -jar proguard.jar @obfuscate.pro
cd ..
# rm -dr classes
rm manif.inf
./run.sh
