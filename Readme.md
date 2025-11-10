### ReparaFacil SPA ###

## 1. Caso elegido y alcance

**Caso: ReparaFacil SPA**

ReparaFácil ofrece servicios de reparación a domicilio para electrodomésticos y
oficios técnicos. La empresa quiere implementar un sistema que conecte técnicos con
clientes de forma eficiente, gestionando agendas, diagnósticos y garantías, apoyando el
trabajo independiente y fomentando el consumo responsable al extender la vida útil de los
aparatos.

**Entidades principales:**

    - Usuario (Cliente)
    - Servicio
    - Agenda
    - Notificacion de confirmacion

**Alcance esperado de la entrega**

Incluya diseño y navegacion de la interfaz, validaciones de formularios, manejo de estados, almacenamiento loca,
uso de funciones nativas del dispositivo (camara, galeria y notificacion), consumo de API.

## 2. Requisitos y ejecuciones

**Tecnologias utilizadas:**

    - Kotlin + Jetpack Compose
    - Navigation Compose
    - ViewModel + LiveData
    - Retrofit y OkHttp para conectarse a la API
    - Kotlinx Corutinas
    - DataStore para guardar datos de manera local
    - Coli para  mostrar imagenes
    - NotificationManager para notificaciones locales
    - Accompanist Permissions para gestionar permisos del sistema

**Cómo instalar y ejecutar:**

**Clona el repositorio:**
git clone https://github.com/Thragg969/ReparaFacilSPA-App-Moviles

Abre el proyecto en Android Studio.

Espera a que se descarguen todas las dependencias y se sincronice Gradle.

Corre la app en un emulador o dispositivo físico desde Android Studio.

Y listo asi corre el emulador y tiene la aplicacion :D

## 3. Arquitectura y flujo general

La app esta construida usando el patron MVVM (Model-View-ViewModel), aprovechando Jetpack Compose
para separar bien la logica de la interfaz.

**Estructura del proyecto:**

    - Data/ Manejo de API y almacenamiento local (Con DataStore).

    - UI/ Pantallas, componentes visuales y navegacion.

    - Utils/ Funciones de apoyo como validaciones y permisos.

**Menjo de estado:**
Cada pantalla reacciona a su estado actual (cargando, exito o error) usando StateFlow o LiveData.
La informacion del usuario logueado se guarda en DataStore.

**Navegacion:**
Se una NavHost para movernos entre las pantallas principales: Login, Registro, Servicios, Agendar y Perfil.
El boton de "Atras" esta controlado para que siempre tenga sentido dentro del flujo.

## 4. Funcionalidades claves

    - Formularios validados (login y registro):

    - Se valida el correo y contraseña en tiempo real.

    - No se puede continuar hasta que todos los campos estén bien.

    - Los errores se muestran de forma clara.

    - Navegación y manejo del backstack:

    - Flujo completo desde el login hasta el perfil del usuario.

    - Se controla bien el historial de navegación y el botón de regreso.

**Gestion de estado en tiempo real:**
Los estados (como cargando o con error) se muestran al usuario conforme cambian.

**Funciones del dispositivo:**
    - Camara y galeria: el usuario puede cambiar su foto de perfil tomando una nueva eligiendo una desde su galeria.

    - Notificaciones locales: Cuando se agenda un servicio, lleega una notificacion con el mensaje "Servicio agendado correctamente".

    - Se piden los permisos de forma adecuada, y se maneja si el usuario los rechaza al momento de querer aceptar acceder a la camara.

    - Animaciones con sentido: Trancisiones suaves entre pantallas y efectos visuales al mostrar servicios, que ayuden a entender mejor al interfaz

    - Consumo de API: Se conecta a la API de prueba de Xano.

## 5. Endpoints utilizados

**Base URL: https://x8ki-letl-twmt.n7.xano.io/api:Rfm_61dW**

| Método | Ruta | Descripción | Respuesta esperada |
| ------ | ---- | ------------ | ------------------ |
| POST | /auth/signup | Registra un nuevo usuario | 201 { authToken, user } |
| POST | /auth/login | Inicia sesión y obtiene token | 200 { authToken, user } |
| GET | /auth/me | Retorna los datos del usuario autenticado | 200 { id, email, name, avatarUrl } |

## 6. Flujos de usuario

**Registro:**

El usuario abre la app y elige "Crear cuenta", llena sus datos y validan antes de enviarlos.
Si todo esta correcto, se registra y se guarda su sesion, lo manda directamente a a la pantalla principal.

**Inicio de sesion:**
Elige "Iniciar sesion", ingresa sus datos y accede.
Si los datos son validos, se guarda el token y se muesta su perfil, si muesta un error Se notifica en la pantalla.

**Agendamiento de servicio:**
Elige un servicio, selecciona fecha y hora, confirma y le llega una notificacion local indicando que fue exitoso.

**Perfil:**
Desde esta pantalla puede cambiar su foto, tiene opcion de tomar una nueva o elegir una ya guardada.

## 7.Observaciones finales

Este proyecto se desarrollo como parte de la evaluacion 2.

La aplicacion cumple con los requisitos
una interfaz coherente, navegacion fluida, almacenamiento local efectivo, integracion de funciones del celular,
animaciones utilies y conexion con una API.
