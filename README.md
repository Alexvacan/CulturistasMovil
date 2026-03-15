# Guía de Desarrollo

¡Equipo! Para que la app sea uniforme, sigan estas reglas de oro:

### 1. Componentes Shared
No creen botones o campos desde cero. Usen los de la carpeta `shared`:
- `ParkButton`: Botón azul (por defecto) o azul claro (`isSecondary = true`).
- `ParkTextField`: Para todos los inputs.
- `ParkHeader`: Cabecera con título y flecha atrás.

### 2. Cómo armar una pantalla de EasyPark
Para que todo encaje, usa este "esqueleto" en tu `Screen`:
```kotlin
Scaffold(
    topBar = {
        ParkHeader(
            title = "Título de la Pantalla",
            onBackClick = { navController.popBackStack() }, // Poner null si no quieres flecha
            onNotificationClick = { /* Acción */ } // Poner null si no quieres campana
        )
    },
    bottomBar = {
        // Elige el footer según el rol del usuario en tu pantalla
        DriverFooter(currentScreen = "home_driver", onNavigate = { /* nav */ })
    }
) { padding ->
    Column(modifier = Modifier.padding(padding)) {
        // TU CONTENIDO AQUÍ
    }
}
```
Nota sobre Iconos: En los footers he usado ColorFilter.tint(color). Esto significa que aunque tu imagen JPG sea negra o azul, el código la pintará automáticamente de Gris o Azul según si está seleccionada o no.

### 3. Imágenes y Iconos
**IMPORTANTE:** No usen iconos del sistema. Todo se maneja con imágenes en `drawable`.
Uso: `painterResource(Res.drawable.nombre_imagen)`.

### 4. Arquitectura y Koin
Cada vez que hagan una pantalla, deben actualizar el **`PresentationModule.kt`**. Si se olvidan de registrar el ViewModel o el UseCase, la app se cerrará al navegar.
```kotlin
// Ejemplo
viewModelOf(::RegistroViewModel)
factory { RegistrarVehiculoUseCase(get()) }
```
### 5. Textos Estaticos
Nada de "hardcoded text". Todo texto estatico debe estar en strings.xml y llamarse así:
stringResource(Res.string.mi_texto)

### 6. Datos (Mocks)
Como no tenemos base de datos real todavía, en el PresentationModule registren un Repositorio falso (Mock) que devuelva datos estáticos para poder probar las pantallas.