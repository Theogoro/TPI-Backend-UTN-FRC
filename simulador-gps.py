import matplotlib.pyplot as plt
from geopy.distance import geodesic
import matplotlib.patches as patches
import requests
import tkinter as tk
from tkinter import simpledialog

URL_BACKEND = "http://localhost:8082"
URL_CONFIGURACION = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/"

data = requests.get(URL_CONFIGURACION).json()

# Coordenadas de la agencia y radio
coordenadas_agencia = (data["coordenadasAgencia"]["lat"], data["coordenadasAgencia"]["lon"])
radio_admitido_km = data["radioAdmitidoKm"]

# Crear figura y ejes
fig, ax = plt.subplots(figsize=(8, 8))

# Dibujar el círculo de radio permitido alrededor de la agencia
agencia_circle = plt.Circle((coordenadas_agencia[1], coordenadas_agencia[0]), 
                            radio_admitido_km / 111,  # Aproximación: 1 grado ≈ 111 km
                            color='lightblue', alpha=0.5, label="Radio Admitido")
ax.add_patch(agencia_circle)

# Marcar la ubicación de la agencia
plt.plot(coordenadas_agencia[1], coordenadas_agencia[0], 'bo', label="Agencia")

# Dibujar cada zona restringida
for zona in data["zonasRestringidas"]:
    noroeste = (zona["noroeste"]["lat"], zona["noroeste"]["lon"])
    sureste = (zona["sureste"]["lat"], zona["sureste"]["lon"])
    rect_width = sureste[1] - noroeste[1]
    rect_height = noroeste[0] - sureste[0]

    # Agregar el rectángulo al gráfico
    rect = patches.Rectangle((noroeste[1], sureste[0]), rect_width, rect_height,
                             linewidth=1, edgecolor='r', facecolor='red', alpha=0.3, label="Zona Restringida")
    ax.add_patch(rect)

# Configurar límites del gráfico con un margen más amplio
zoom_factor = 0.05  # Incrementa el valor para ver un área mayor

ax.set_xlim(coordenadas_agencia[1] - zoom_factor, coordenadas_agencia[1] + zoom_factor)
ax.set_ylim(coordenadas_agencia[0] - zoom_factor, coordenadas_agencia[0] + zoom_factor)

plt.xlabel("Longitud")
plt.ylabel("Latitud")
plt.legend()
plt.title("Ubicación de Agencia, Radio Permitido y Zonas Restringidas")
plt.grid()

# Definir la función de clic
def onclick(event):
    if event.inaxes is not None:
        lat = event.ydata
        lon = event.xdata

        root = tk.Tk()
        root.withdraw()  # Oculta la ventana principal de Tkinter
        vehicle_id = simpledialog.askstring("ID del Vehículo", "Por favor, ingrese el ID del vehículo:")
        
        if vehicle_id:

            print(f"Coordenadas del clic: Latitud = {lat}, Longitud = {lon}")
            print(f"ID del Vehículo ingresado: {vehicle_id}")

            data = {
                "idVehiculo": vehicle_id,
                "latitud": lat,
                "longitud": lon
            }
            print(data)
            post_res = requests.post(f"{URL_BACKEND}/api/v1/posiciones", json=data)
            print(post_res.status_code)
            print(post_res.json())

        root.destroy()

fig.canvas.mpl_connect('button_press_event', onclick)


plt.savefig("grafico.png")
plt.show()
