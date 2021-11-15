# NexoTest
Listado de personas con sus correspondientes direcciones: METHOD GET (Respetar convenciones en la url)
```
http://localhost:8080/personas/
```
Listado filtrado por A B y D: METHOD GET (Utilizar query parameters y respetar convenciones)
```
http://localhost:8080/personas/dni/{id}
```
```
http://localhost:8080/personas/nombre/{nombre}
```
```
http://localhost:8080/personas/edad/{edad}
```
Alta de persona: METHOD POST (Respetar convenciones en la url). Que reciba la foto también.
```
{
        "dni": 1,
        "nombre": "NameTest",
        "apellido": "LastNameTest",
        "edad": 27,
        "foto": null,
        "direccionList": [
            {
                "id": 1,
                "calle": "Chacabuco",
                "numCalle": 7,
                "ciudad": "cba",
                "persona": 1
            }
        ]
    }

```
Modificación de persona: METHOD PUT (Respetar convenciones en la url)
```
{
        "dni": 1,
        "nombre": "NameTest",
        "apellido": "LastNameTest",
        "edad": 27,
        "foto": null,
        "direccionList": [
            {
                "id": 1,
                "calle": "Chacabuco",
                "numCalle": 7,
                "ciudad": "cba",
                "persona": 1
            }
        ]
    }
```
Eliminación de persona: METHOD DELETE  (Respetar convenciones en la url). 
6)	Obtener un persona por ID
```
http://localhost:8080/personas/{id}

```
8) 	Exportar listado de personas a un archivo csv

```
http://localhost:8080/personas/export

```
