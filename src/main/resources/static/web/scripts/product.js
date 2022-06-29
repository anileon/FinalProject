const urlParams = new URLSearchParams(window.location.search);
const getID = urlParams.get('id');

Vue.createApp({
    data() {
        return {
            product: [],

            scrolled: false,
            searchText: "",
            cantidad: 1,
            gender: "",
            auxiliar: [],
            tipoSeleccionado: [],
            precioSeleccionado: "Relevant",
            carouselValue: 0,

            productosObtenidos: [],
            arrayDeProductos: [],
            arrayDeMotos: [],
            productosDelCarrito: [],
            productosGeneral: [],
            totalCarrito: [],
            images: [],
            idProducto: "",
            total: "",
        }
    },

    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

    created() {
        axios.get(`/api/products/${getID}`)
        .then(res => {
            console.log(res.data);
            this.product = res.data
            this.images.push(this.product.urlImg)

            this.productosObtenidos = JSON.parse(localStorage.getItem("productos-carrito"))
            if (this.productosObtenidos) {
                this.productosDelCarrito = this.productosObtenidos
            }

            this.arrayDeMotos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
            this.arrayDeProductos = JSON.parse(localStorage.getItem("array-productos") || "[]")
            this.productosGeneral = JSON.parse(localStorage.getItem("carrito") || "[]")
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
                    idProducto: id,
                    cantidad: cantidad
                }
                this.arrayDeProductos.push(productoModificado)

                localStorage.setItem("array-productos", JSON.stringify(this.arrayDeProductos))
                localStorage.setItem("productos-carrito", JSON.stringify(this.productosDelCarrito))
                localStorage.setItem("carrito", JSON.stringify(this.productosGeneral))
            } else {
                console.log("asdasd");
            }
        },

        plusCounter(){
            if (this.cantidad < this.product.stock) {
                this.cantidad += 1
            }
        },

        minusCounter(){
            this.cantidad -= 1

            if (this.cantidad == 0) {
                this.cantidad = 1
            }
        },

        imagenAnterior() {
            this.carouselValue -= 1
            let imgCarousel0 = document.querySelector("#img-carousel-0")
            let imgCarousel1 = document.querySelector("#img-carousel-1")
            let imgCarousel2 = document.querySelector("#img-carousel-2")
            if (this.carouselValue < 0) {
                this.carouselValue = this.images.length
                if (imgCarousel0 != null) {
                    imgCarousel0.classList.add("display-none")
                }
                if (imgCarousel2 != null) {
                    imgCarousel2.classList.remove("display-none")
                } else {
                    imgCarousel1.classList.remove("display-none")
                }
            } else if (this.carouselValue == 0) {
                if (imgCarousel1 != null) {
                    imgCarousel1.classList.add("display-none")
                }
                if (imgCarousel0 != null) {
                    imgCarousel0.classList.remove("display-none")
                }
            } else if (this.carouselValue == 1) {
                if (imgCarousel2 != null) {
                    imgCarousel2.classList.add("display-none")
                }
                if (imgCarousel1 != null) {
                    imgCarousel1.classList.remove("display-none")
                }
            }
        },
        imagenSiguiente() {
            this.carouselValue += 1
            let imgCarousel0 = document.querySelector("#img-carousel-0")
            let imgCarousel1 = document.querySelector("#img-carousel-1")
            let imgCarousel2 = document.querySelector("#img-carousel-2")

            if (this.carouselValue == 1) {
                if (imgCarousel0 != null) {
                    imgCarousel0.classList.add("display-none")
                }
                if (imgCarousel1 != null) {
                    imgCarousel1.classList.remove("display-none")
                }
            } else if (this.carouselValue >= this.images.length) {
                this.carouselValue = 0
                if (imgCarousel1 != null) {
                    imgCarousel1.classList.add("display-none")
                }
                if (imgCarousel2 != null) {
                    imgCarousel2.classList.add("display-none")
                }
                if (imgCarousel0 != null) {
                    imgCarousel0.classList.remove("display-none")
                }
            } else if (this.carouselValue == 2) {
                if (imgCarousel1 != null) {
                    imgCarousel1.classList.add("display-none")
                }
                if (imgCarousel2 != null) {
                    imgCarousel2.classList.remove("display-none")
                }
            }
        },
        imagenSlide(index) {
            let imgCarousel0 = document.querySelector("#img-carousel-0")
            let imgCarousel1 = document.querySelector("#img-carousel-1")
            let imgCarousel2 = document.querySelector("#img-carousel-2")
            if (index == 0) {
                this.carouselValue = 0
                if (imgCarousel1 != null) {
                    imgCarousel1.classList.add("display-none")
                }
                if (imgCarousel2) {
                    imgCarousel2.classList.add("display-none")
                }
                if (imgCarousel0 != null) {
                    imgCarousel0.classList.remove("display-none")
                }
            } else if (index == 1) {
                this.carouselValue = 1
                if (imgCarousel2) {
                    imgCarousel2.classList.add("display-none")
                }
                if (imgCarousel0 != null) {
                    imgCarousel0.classList.add("display-none")
                }
                if (imgCarousel1 != null) {
                    imgCarousel1.classList.remove("display-none")
                }
            } else {
                this.carouselValue = 2
                if (imgCarousel0 != null) {
                    imgCarousel0.classList.add("display-none")
                }
                if (imgCarousel1 != null) {
                    imgCarousel1.classList.add("display-none")
                }
                if (imgCarousel2) {
                    imgCarousel2.classList.remove("display-none")
                }
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

        toggleCart() {
            let element = document.querySelector(".carrito")
            element.classList.toggle("oculto")
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

        handleScroll() {
            this.scrolled = window.scrollY > 0;
        },
    },

    computed: {
        
    }

}).mount('#app')