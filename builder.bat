@echo off
set "error=f"

set "inno=C:\Program Files (x86)\Inno Setup 6\ISCC.exe"
set "launch4j=C:\Program Files (x86)\Launch4j\launch4j.jar"

:: Borramos los archivos para evitar problemas...
set "jar=TFG\06TFGPlay-0.0.1-SNAPSHOT.jar"
set "installer=TFG\TFGPlay-installer.exe"
set "Database=TFG\TFGPlay\Database"
set "DatabaseVacia=TFG\TFGPlay\DatabaseVacia"
set "exe=TFG\TFGPlay\TFGPlay.exe

:: https://stackoverflow.com/questions/16552479/delete-files-in-batch-with-without-error-message
IF EXIST "%jar%" (
	echo borrando JAR...
	del "%jar%"
)
IF EXIST "%installer%" (
	echo borrando instalador...
	del "%installer%"
)
IF EXIST "%Database%" (
	echo borrando Database...
	del "%Database%"
)
IF EXIST "%DatabaseVacia%" (
	echo borrando DatabaseVacia...
	del "%DatabaseVacia%"
)
IF EXIST "%exe%" (
	echo borrando exe...
	del "%exe%"
)

echo Pulsa una tecla para Crear el JAR y exportar las BDs...
pause >nul
:: Al usar call, se llama al comando y se espera a que termine la ejecución antes de continuar con el siguiente comando
call .\mvnw.cmd clean package

if %ERRORLEVEL% NEQ 0 (
    echo Error: Ha ocurrido un problema durante la creación del JAR y exportación de las BDs.
    goto :error
)

echo.
echo JAR creado y BDs exportadas, pulse una tecla para crear el exe...
echo.
pause >nul
cls

echo Creando exe
cd TFG
call java -jar "%launch4j%" jarToExe.xml

if %ERRORLEVEL% NEQ 0 (
    echo Error: Ha ocurrido un problema durante la creación del exe.
    goto :error
)

echo.
echo Exe creado, pulse una tecla para crear el instalador...
echo.
pause >nul
cls

echo Creando instalador, esto puede tomar varios minutos
call "%inno%" script-installer.iss

if %ERRORLEVEL% NEQ 0 (
    echo Error: Ha ocurrido un problema durante la creación del instalador.
    goto :error
)

echo.
echo Instalador creado, pulse una tecla para abrir la ubicacion del archivo
echo.
goto :end

:error
set "error=t"
echo.
echo terminado por un error, pulse una tecla para cerrar
echo.

:end
pause >nul
if %error% == f (
	explorer .
)
