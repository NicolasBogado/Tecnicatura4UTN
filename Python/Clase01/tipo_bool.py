
# bool contiene los valores de True y False
# los tipos numéricos es false para el 0 (cero), y true para los demás valores
valor = 0
resultado = bool(valor)
print(f'valor:{valor}, resultado:{resultado}')

valor = 1
resultado = bool(valor)
print(f'valor:{valor}, resultado:{resultado}')

# tipo string -> False '', true demás valores
valor = ''
resultado = bool(valor)
print(f'valor:{valor}, resultado:{resultado}')

valor = 'Hola'
resultado = bool(valor)
print(f'valor:{valor}, resultado:{resultado}')

# tipo colecciones -> False para colecciones vacias
# tipo colecciones -> True para todas las demás
# Lista
valor = []
resultado = bool(valor)
print(f'valor de una lista vacía:{valor}, resultado:{resultado}')

valor = [2,3,4]
resultado = bool(valor)
print(f'valor de una lista con elementos:{valor}, resultado:{resultado}')

# Tupla
valor = ()
resultado = bool(valor)
print(f'valor de una tupla vacía:{valor}, resultado:{resultado}')

valor = (5,)
resultado = bool(valor)
print(f'valor de una tupla con elementos:{valor}, resultado:{resultado}')

# Diccionario
valor = {}
resultado = bool(valor)
print(f'valor de un diccionario vacío:{valor}, resultado:{resultado}')

valor = {'Nombre':'Juan', 'Apellido':'Perez'}
resultado = bool(valor)
print(f'valor de un diccionario con elementos:{valor}, resultado:{resultado}')

# Sentencias de control bool
if (1,):
    print('Regresa verdadero')
else:
    print('Regresa falso')

# Ciclos
variable = 3
while variable:
    print('Regresa verdadero')
    break
else:
    print('Regresa falso')
