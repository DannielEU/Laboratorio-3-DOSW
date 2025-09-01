# Estudiante: Daniel Eduardo Useche Pinilla
# Estas son las Historias de Uso que definimos anteriormente 
historias = [
    "HU1 - Crear cuenta válida",
    "HU2 - Consultar saldo",
    "HU3 - Hacer depósito",
    "HU4 - Validar banco",
    "HU5 - Revisar Historial"
]

fibonacci = [1, 2, 3, 5, 8, 13]
num_integrantes = int(input("Ingrese el número de integrantes del equipo: "))
estimaciones = {}

for historia in historias:
    print(f"\n Historia: {historia}")
    consenso = False
    
    while not consenso:
        votos = []
        for i in range(num_integrantes):
            voto = None
            while voto not in fibonacci:
                try:
                    voto = int(input(f"Integrante {i+1}, ingrese su voto {fibonacci}: "))
                    if voto not in fibonacci:
                        print("El valor no es valido. \n")
                    else:
                        print("Valor agregado. \n ")
                        votos.append(voto)
                except ValueError:
                    print("Por favor, ingrese un número válido.")
        if all(v == votos[0] for v in votos):
            puntaje = votos[0]
            print(f"\n Consenso alcanzado. Puntaje asignado: {puntaje}")
            estimaciones[historia] = puntaje
            consenso = True
        else:
            print("\n Votos divergentes Discutan y vuelvan a votar.")

print("\n=== Resumen de Votos del Equpo ===")
for historia, puntaje in estimaciones.items():
    print(f"{historia}: {puntaje}")
