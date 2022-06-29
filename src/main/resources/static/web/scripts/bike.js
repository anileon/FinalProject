const urlParams = new URLSearchParams(window.location.search);
const getID = urlParams.get('id');

Vue.createApp({
    data() {
        return {
            message: 'Hello Vue!',
            producto: [],

            scrolled: false,
            searchText: "",
            cantidad: 1,
            gender: "",
            auxiliar: [],
            tipoSeleccionado: [],
            precioSeleccionado: "Relevant",
            productosObtenidos: [],
            arrayDeProductos: [],

            productosDelCarrito: [],
            productosGeneral: [],
            arrayDeMotos: [],
            totalCarrito: [],
            idProducto: "",
            total: "",
        }
    },



    created() {
        axios.get(`/api/motorcycles/${getID}`)
        .then(res => {
            console.log(res.data);
            this.producto = res.data

            this.productosObtenidos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
            if (this.productosObtenidos) {
                this.productosDelCarrito = this.productosObtenidos
            }


            this.arrayDeProductos = JSON.parse(localStorage.getItem("productos-carrito") || "[]")
            this.productosGeneral = JSON.parse(localStorage.getItem("carrito") || "[]")
            this.arrayDeMotos = JSON.parse(localStorage.getItem("array-motos") || "[]")

            console.log(this.productosGeneral);
        })
    },

    methods: {
        llenarCarrito(producto) {
            let cantidad =  this.cantidad
            producto.cantidad = cantidad

            this.idProducto = this.productosDelCarrito.map(prod => prod.id)

            if(!this.idProducto.includes(producto.id)) {
                this.productosDelCarrito.push(producto)
                this.productosGeneral.push(producto)

                let id = producto.id
                let cantidad = producto.cantidad
                let productoModificado = {
                    id: id,
                    cantidad: cantidad
                }
                this.arrayDeMotos.push(productoModificado)

                localStorage.setItem("array-motos", JSON.stringify(this.arrayDeMotos))
                localStorage.setItem("motos-carrito", JSON.stringify(this.productosDelCarrito))
                localStorage.setItem("carrito", JSON.stringify(this.productosGeneral))
            } else {
                console.log("asdasd");
            }
        },

        plusCounter(){
            if (this.cantidad < this.producto.stock) {
                this.cantidad += 1
            }
        },

        minusCounter(){
            this.cantidad -= 1

            if (this.cantidad == 0) {
                this.cantidad = 1
            }
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

            element.classList.toggle("oculto")

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

            element.classList.toggle("oculto")
        },

        cerrarNavbar(element){
            let elemento = element.target.parentElement.parentElement

            elemento.classList.add("oculto")
        },

        toggleCart() {
            let element = document.querySelector(".carrito")
            element.classList.toggle("oculto")
        },

        handleScroll() {
            this.scrolled = window.scrollY > 0;
        },
    },

    computed: {
        
    },

    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

}).mount('#app')