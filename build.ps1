# Compilation : tous les fichiers .java dans src/main/java
Write-Host "Compilation en cours"
javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName })

# Exécution : lancement de LeanGridApp avec les bons arguments
Write-Host "Execution de LeanGridApp"
java -cp out org.leanGrid.LeanGridApp ImportedFiles/instance1.txt 10

# Lancement du script : .\build.ps1