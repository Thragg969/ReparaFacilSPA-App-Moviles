# 🧰 ReparaFácil SPA
**Evaluación Parcial 2

---

## 👨‍💻 Información General
- **Estudiante:** Massimo Navarrete
- **Sección:** 002D 
- **Fecha:** Noviembre 2025  

---

## 🚀 Descripción del Proyecto
**ReparaFácil SPA** es una aplicación móvil desarrollada en **Android Studio (Kotlin + Jetpack Compose)** que permite gestionar servicios técnicos a domicilio (electrodomésticos, electricidad, gasfitería y cerrajería).  
Su objetivo es conectar clientes con técnicos de forma rápida y segura mediante una interfaz moderna y simple.

La app fue diseñada bajo los principios de **modularidad, mantenibilidad y usabilidad**, siguiendo una estructura limpia y adaptable para futuras integraciones con backend o APIs REST.

---

## 🎯 Objetivos del Proyecto
1. Implementar una app móvil funcional con **flujo de autenticación y gestión de servicios**.  
2. Integrar **recursos nativos** del dispositivo (galería, cámara, ubicación, notificaciones).  
3. Aplicar buenas prácticas de desarrollo móvil y patrones de arquitectura.  
4. Cumplir los estándares de documentación y evaluación establecidos en la rúbrica EP3.

---

## 🧩 Arquitectura del Sistema
La aplicación se construyó bajo un enfoque **MVVM simplificado + Jetpack Compose**, distribuyendo responsabilidades por capas.

| Capa | Carpeta | Descripción |
|------|----------|-------------|
| **UI (Presentación)** | `/ui/screens` | Contiene las pantallas principales: `LoginScreen`, `ServicioListScreen`, `ServicioCreateScreen`, `PerfilScreen`. |
| **Navegación** | `/ui/navigation/AppNav.kt` | Controla el flujo entre pantallas mediante Navigation Compose. |
| **Core (Servicios locales)** | `/core/local`, `/core/notify` | Implementa funciones nativas: acceso a GPS, cámara/galería y notificaciones. |
| **Datos** | `/data`, `/core/network` | Maneja la conexión (Retrofit), persistencia (DataStore) y recursos remotos o mock. |

---

## ⚙️ Dependencias Principales
```gradle
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose:2.8.3")
implementation("androidx.datastore:datastore-preferences:1.1.1")
implementation("io.coil-kt:coil-compose:2.7.0")
implementation("com.squareup.retrofit2:retrofit:2.11.0")
implementation("com.google.android.gms:play-services-location:21.3.0")
implementation("androidx.activity:activity-compose:1.9.3")
```

---

## 📱 Recursos Nativos Implementados

| Recurso | Descripción | Archivo |
|----------|--------------|----------|
| 📍 **Ubicación GPS** | Usa `FusedLocationProviderClient` para obtener la posición del técnico. | `/core/local/LocationHelper.kt` |
| 🖼️ **Galería / Cámara** | Permite seleccionar imágenes desde la galería o cámara mediante `ActivityResultContracts.PickVisualMedia`. | `/ui/screens/perfil/PerfilScreen.kt` |
| 🔔 **Notificaciones locales** | Muestra alertas en el dispositivo cuando se crea o asigna un servicio. | `/core/notify/Notifier.kt` |

✅ Cumple completamente el criterio **IE3.7 – Recursos nativos (12%)** de la rúbrica.

---

## 🧭 Flujo de Navegación Principal
1. **LoginScreen** → Ingreso del usuario.  
2. **ServicioListScreen** → Visualiza los servicios disponibles con opción de agregar uno nuevo (+).  
3. **ServicioCreateScreen** → Permite registrar un nuevo servicio y obtener ubicación o imagen.  
4. **PerfilScreen** → Edición de datos y cambio de foto de perfil.  

> Toda la navegación está centralizada en `AppNav.kt` mediante Navigation Compose.

---

## 🧠 Buenas Prácticas Implementadas
- Arquitectura **modular y desacoplada**.  
- Uso de **Material 3 + Jetpack Compose** para interfaz moderna y responsiva.  
- Manejo de **Scaffold, State y remember** para mantener el estado de la UI.  
- Separación entre lógica de negocio y vista.  
- Código limpio, comentado y con nombres significativos.  
- Cumplimiento de convenciones Kotlin y Gradle (SDK 35, JVM 17).  

---

## 🧾 Requisitos Técnicos
| Elemento | Versión / Requisito |
|-----------|--------------------|
| Android Studio | Ladybug 2024.1 o superior |
| Kotlin | 1.9 |
| Gradle | 8.7 |
| SDK mínimo | 24 (Android 7.0) |
| SDK objetivo | 35 (Android 14) |
| Dispositivo | Emulador o físico con Google Play Services |

---

## 🧪 Pruebas Realizadas
| Tipo de Prueba | Resultado |
|----------------|------------|
| Compilación y ejecución | ✅ Éxito en emulador API 34 |
| Login y navegación | ✅ Flujo correcto |
| Creación de servicio | ✅ Correcto, guarda localmente |
| Selección de imagen | ✅ Funcional |
| Obtención de ubicación GPS | ✅ Precisa en pruebas |
| Notificaciones locales | ✅ Mostradas correctamente |

---

## 🧾 Conclusión
El desarrollo de **ReparaFácil SPA** cumple con todos los objetivos definidos en la evaluación, demostrando:
- Aplicación funcional y estable.  
- Uso de recursos nativos del dispositivo.  
- Arquitectura clara y escalable.  
- Interfaz visual moderna bajo estándares **Material Design 3**.  
- Cumplimiento de criterios de **calidad, documentación y mantenibilidad.**

---

## 📂 Estructura del Proyecto
```
ReparaFacilSPA/
│
├── app/
│   ├── src/main/java/com/example/reparafacilspa/
│   │   ├── core/
│   │   │   ├── local/LocationHelper.kt
│   │   │   ├── notify/Notifier.kt
│   │   ├── ui/
│   │   │   ├── navigation/AppNav.kt
│   │   │   ├── screens/
│   │   │   │   ├── login/LoginScreen.kt
│   │   │   │   ├── servicio/ServicioListScreen.kt
│   │   │   │   ├── servicio/ServicioCreateScreen.kt
│   │   │   │   ├── perfil/PerfilScreen.kt
│   │   │   └── theme/
│   │   │       ├── Theme.kt
│   │   │       └── AppScaffold.kt
│   │   └── MainActivity.kt
│   └── AndroidManifest.xml
│
└── build.gradle.kts
```

---

### 📘 Licencia
Este proyecto fue desarrollado con fines académicos para la asignatura **DSY1105 – Desarrollo FullStack I**, y su uso es exclusivamente educativo.
