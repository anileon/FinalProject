const urlParams = new URLSearchParams(window.location.search);
const getGender = urlParams.get('gender');
const getCategory = urlParams.get('category');

Vue.createApp({
    data() {
        return {
            message: 'Hello Vue!',
            productos: [],

            scrolled: false,
            searchText: "",
            gender: "",
            auxiliar: [],
            tipoSeleccionado: [],
            precioSeleccionado: "Relevant",

            arrayDeProductos: [],
            arrayDeMotos: [],
            productosDelCarrito: [],
            productosGeneral: "",
            totalCarrito: [],
            idProducto: "",
            total: "",
        }
    },

    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

    created() {
        axios.get("/api/products")
        .then(res => {
            this.productos = res.data
            this.auxiliar = this.productos

            console.log(res.data);
        })

        if (getGender == "MALE") {
            this.gender = "MALE"
        }
        if (getGender == "FEMALE") {
            this.gender = "FEMALE"
        }
        if (getCategory == "Gloves") {
            this.tipoSeleccionado.push("Gloves")
        }
        if (getCategory == "Jacket") {
            this.tipoSeleccionado.push("Jacket")
        }
        if (getCategory == "Helmet") {
            this.tipoSeleccionado.push("Helmet")
        }

        this.arrayDeProductos = JSON.parse(localStorage.getItem("productos-carrito") || "[]")
        this.arrayDeMotos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
        this.arrayMotos = JSON.parse(localStorage.getItem("array-motos") || "[]")
        this.arrayProductos = JSON.parse(localStorage.getItem("array-productos") || "[]")
        this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length

        setTimeout(() => {
            let loader = document.querySelector(".bike-loader")
            loader.classList.add("oculto")
        }, 3000);
    },

    methods: {
        borrarCarrito(producto) {
            if (producto.hasOwnProperty('id') && producto.hasOwnProperty('size')) {
                let arrFiltrado = this.arrayDeProductos.filter(obj => obj.id != producto.id)
                let arrayOBJ = this.arrayProductos.filter(obj => obj.idProducto != producto.id)

                localStorage.setItem("productos-carrito", JSON.stringify(arrFiltrado))
                localStorage.setItem("array-productos", JSON.stringify(arrayOBJ))

                this.arrayProductos = arrayOBJ
                this.arrayDeProductos = arrFiltrado
                this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length
                this.total = this.total - this.subtotal(producto.price, producto.cantidad)
            }

            if (producto.hasOwnProperty('id') && producto.hasOwnProperty('model')) {
                let arrFiltrado = this.arrayDeMotos.filter(obj => obj.id != producto.id)
                let arrayOBJ = this.arrayDeMotos.filter(obj => obj.id != producto.id)
                
                localStorage.setItem("motos-carrito", JSON.stringify(arrFiltrado))
                localStorage.setItem("array-motos", JSON.stringify(arrayOBJ))

                this.arrayMotos = arrayOBJ
                this.arrayDeMotos = arrFiltrado
                this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length
                this.total = this.total - this.subtotal(producto.price, producto.cantidad)
            }
        },
        addFavorite(id){
            console.log(id);
            let unFavorite = document.querySelector("#unFavoriteMobile" + id)
            let favorite = document.querySelector("#favoriteMobile" + id)
            unFavorite.classList.remove("favoriteMobileChecked")
            unFavorite.classList.add("favoriteMobileUnChecked")
            favorite.classList.remove("favoriteMobileUnChecked")
            favorite.classList.add("favoriteMobileChecked")
        },
        removeFavorite(id){
            let unFavorite = document.querySelector("#unFavoriteMobile" + id)
            let favorite = document.querySelector("#favoriteMobile" + id)
            favorite.classList.remove("favoriteMobileChecked")
            favorite.classList.add("favoriteMobileUnChecked")
            unFavorite.classList.remove("favoriteMobileUnChecked")
            unFavorite.classList.add("favoriteMobileChecked")
        },
        goBikePage(){
            let bike = document.querySelector(".cartaCatalogo")
            window.location.href="/web/bike.html"
        },

        toggleNavbar(){
            let nav = document.querySelector(".navbar")
            let btn = document.querySelector(".nav-menu-btn")

            nav.classList.toggle("oculto")

            console.log(btn.textContent == "menu");
            if(btn.textContent == "menu") {
                btn.textContent = "close"
            }else if(btn.textContent == "close") {
                btn.textContent = "menu"
            }
        },

        toggleNavItem(target){
            let element = document.querySelector(target)
            let moto = document.querySelector(".nav-motos")
            let hombre = document.querySelector(".nav-hombre")
            let mujer = document.querySelector(".nav-mujer")
            let experiencia = document.querySelector(".nav-experiencia")
            let contacto = document.querySelector(".nav-contact")

            if (!moto.classList.contains("oculto")) {
                moto.classList.toggle("oculto")
            } 
            if (!hombre.classList.contains("oculto")) {
                hombre.classList.toggle("oculto")
            }
            if (!mujer.classList.contains("oculto")) {
                mujer.classList.toggle("oculto")
            }
            if (!experiencia.classList.contains("oculto")) {
                experiencia.classList.toggle("oculto")
            }
            if (!contacto.classList.contains("oculto")) {
                contacto.classList.toggle("oculto")
            } 

            element.classList.remove("oculto")
        },

        cerrarNavbar(element){
            let elemento = document.querySelector(element)
            elemento.classList.add("oculto")
        },

        handleScroll() {
            this.scrolled = window.scrollY > 0;
        },

        toggleFilter() {
            let filtro = document.querySelector(".box-de-filtro")
            filtro.classList.toggle("oculto")
        },

        subtotal(precio, cantidad) {
            let price = precio
            let amount = cantidad
            let total = price * amount

            if (this.totalCarrito.length < this.productosGeneral.length) {
                this.totalCarrito.push(total)
            }
            
            if (this.totalCarrito.length <= this.productosGeneral.length) {
                this.total = this.totalCarrito.reduce((a, b) => a + b, 0)
            }
            return total
        },

        toggleCart() {
            let element = document.querySelector(".carrito")
            element.classList.toggle("oculto")
        },

        modificarProducto(){
            let abrirModal = document.querySelector('.modal-modificar-producto')
            abrirModal.classList.remove('modalOFF')
            abrirModal.classList.add('modalON')
            abrirModal.classList.remove('display-none')
        },
        cerrarModalModificar(){
            let cerrarModal = document.querySelector('.modal-modificar-producto')
            cerrarModal.classList.remove("modalON")
            cerrarModal.classList.add("modalOFF")
            setTimeout(() => {
                cerrarModal.classList.add('display-none')
            }, 500);
        },
    },

    computed: {
        filterChange(){
            this.auxiliar = []

            console.log(this.auxiliar);

            if (this.precioSeleccionado == "Relevant") {
                this.auxiliar = this.productos
            }

            if (this.precioSeleccionado == "Least") {
                this.auxiliar = this.productos.sort((a, b) => a.price - b.price)
            }

            if (this.precioSeleccionado == "Most") {
                this.auxiliar = this.productos.sort((a, b) => b.price - a.price)
            }

            console.log(this.auxiliar);

            if (this.gender == "FEMALE") {
                this.auxiliar = this.productos.filter(prod => prod.genderType == "FEMALE")
            }
            if (this.gender == "MALE") {
                this.auxiliar = this.productos.filter(prod => prod.genderType == "MALE")
            }

            let auxiliar = []
            if (this.searchText != "") {
                this.productos.filter(prod => {
                    if (prod.description.toUpperCase().includes(this.searchText.toUpperCase())) {
                        auxiliar.push(prod)
                        this.auxiliar = auxiliar
                    }
                })
            }

            let aux = []
            if (this.tipoSeleccionado.length > 0) {
                this.productos.forEach(prod => {
                    this.tipoSeleccionado.forEach(check => {
                        prod.type == check ? aux.push(prod) : null
                    })
                })
                this.auxiliar = aux

                if (this.gender == "FEMALE") {
                    this.auxiliar = this.auxiliar.filter(prod => prod.genderType == "FEMALE")
                }
                if (this.gender == "MALE") {
                    this.auxiliar = this.auxiliar.filter(prod => prod.genderType == "MALE")
                }
    
                if (this.precioSeleccionado == "Relevant") {
                    this.auxiliar = this.productos
                }
    
                if (this.precioSeleccionado == "Least") {
                    this.auxiliar = this.auxiliar.sort((a, b) => a.price - b.price)
                }
    
                if (this.precioSeleccionado == "Most") {
                    this.auxiliar = this.auxiliar.sort((a, b) => b.price - a.price)
                }
            }
        }
    }

}).mount('#app')