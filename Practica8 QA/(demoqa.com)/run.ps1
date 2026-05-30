$libDir = "lib"
New-Item -ItemType Directory -Force -Path $libDir | Out-Null

$dependencies = @(
    @{url="https://repo1.maven.org/maven2/com/github/javafaker/javafaker/1.0.2/javafaker-1.0.2.jar"; file="javafaker-1.0.2.jar"},
    @{url="https://repo1.maven.org/maven2/org/json/json/20231013/json-20231013.jar"; file="json-20231013.jar"},
    @{url="https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.18.0/selenium-server-4.18.1.jar"; file="selenium-server-4.18.1.jar"}
)

foreach ($dep in $dependencies) {
    $path = Join-Path -Path $libDir -ChildPath $dep.file
    if (-not (Test-Path -LiteralPath $path)) {
        Write-Host "Descargando $($dep.file)..."
        Invoke-WebRequest -Uri $dep.url -OutFile $path -UseBasicParsing
    }
}

$classpath = "lib\*"

Write-Host "Compilando POM..."
javac -cp "$classpath" TextBoxPage.java TextBoxTestPOM.java
if ($?) {
    Write-Host "Ejecutando TextBoxTestPOM..."
    java -cp ".;$classpath" TextBoxTestPOM
}
