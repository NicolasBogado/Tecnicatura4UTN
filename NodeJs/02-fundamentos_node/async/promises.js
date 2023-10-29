function hola(nombre){
    return new Promise(function (resolve,reject){
        setTimeout(function() {
            console.log("hola " + nombre);
            resolve(nombre);
        }, 1000); 
    });
}

function hablar(nombre){
    return new Promise( (resolve, reject) => { // usamos la sintaxis ES6
        setTimeout(function() {
            console.log('bla bla bla');
            resolve(nombre);
            }, 1000);
    });  
}

function adios(nombre){
    return new Promise(function (resolve, reject){
            setTimeout(function() {
            console.log('Adios '+ nombre)
            //resolve();
            reject('hay un error');
        }, 1000);
    });
}

// llamamos a la funcion
console.log('Iniciando el proceso...')
hola('Nicolas')
    .then(hablar)
    .then(adios)
    .then((nombre) =>{
       console.log('Terminando el proceso...');     
    })
    .catch((error)=>{
        console.log('ha habido un error: ');
        console.log(error);
    })