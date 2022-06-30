
Vue.createApp({
    data() {
        return {
            message: 'Hello Vue!',
            producto: [],

            scrolled: false,
            loggedIn: false,
            searchText: "",
            cantidad: 1,
            gender: "",
            auxiliar: [],
            tipoSeleccionado: [],
            precioSeleccionado: "Relevant",
            arrayDeProductos: [],

            productosGeneral: [],
            arrayDeMotos: [],
            totalCarrito: [],
            idProducto: "",
            total: "",
        }
    },

    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

    created() {
        axios.get("/api/current/clients")
        .then(res => {
            this.loggedIn = !this.loggedIn
            console.log(res)
        })
        .catch(err => console.log(err))

        setTimeout(() => {
            let loader = document.querySelector(".bike-loader")
            loader.classList.add("oculto")
        }, 4000);


        this.arrayDeMotos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
        this.arrayDeProductos = JSON.parse(localStorage.getItem("productos-carrito") || "[]")
        this.arrayMotos = JSON.parse(localStorage.getItem("array-motos") || "[]")
        this.arrayProductos = JSON.parse(localStorage.getItem("array-productos") || "[]")
        this.productosGeneral = this.arrayDeMotos.length + this.arrayDeProductos.length
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

        subtotal(precio, cantidad) {
            let price = precio
            let amount = cantidad
            let total = price * amount

            if (this.totalCarrito.length < this.productosGeneral) {
                this.totalCarrito.push(total)
            }
            
            if (this.totalCarrito.length <= this.productosGeneral) {
                this.total = this.totalCarrito.reduce((a, b) => a + b, 0)
            }
            return total
        },

        realizarCompra() {
            let obj = {
                products: this.productosVenta,
                motors: this.motosVenta
            }
            
            axios.post("/api/comprar", obj)
                .then(res => console.log("FUNCIONA"))
                .catch(err => console.log(err))

            localStorage.clear()
            window.location.reload()
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
    },

    computed: {
        
    }

}).mount('#app')