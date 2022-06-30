const urlParams = new URLSearchParams(window.location.search);
const getID = urlParams.get('id');

Vue.createApp({
    data() {
        return {
            message: 'Hello Vue!',
            bike: [],

            scrolled: false,
            searchText: "",
            cantidad: 1,
            carouselValue: 0,
            gender: "",
            images: [],
            auxiliar: [],
            tipoSeleccionado: [],
            precioSeleccionado: "Relevant",
            productosObtenidos: [],
            arrayDeProductos: [],

            productosDelCarrito: [],
            productosGeneral: "",
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
            this.bike = res.data
            this.images.push(this.bike.images)

            this.arrayDeMotos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
            if (this.arrayDeMotos) {
                this.productosDelCarrito = this.arrayDeMotos
            }


            this.arrayDeProductos = JSON.parse(localStorage.getItem("productos-carrito") || "[]")
            this.arrayMotos = JSON.parse(localStorage.getItem("array-motos") || "[]")
            this.arrayProductos = JSON.parse(localStorage.getItem("array-productos") || "[]")
            this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length

            setTimeout(() => {
                let loader = document.querySelector(".bike-loader")
                loader.classList.add("oculto")
            }, 3000);
        })
    },

    methods: {
        llenarCarrito(producto) {
            let cantidad =  this.cantidad
            producto.cantidad = cantidad

            this.idProducto = this.productosDelCarrito.map(prod => prod.id)

            if(!this.idProducto.includes(producto.id)) {
                this.productosDelCarrito.push(producto)
                let id = producto.id
                let cantidad = producto.cantidad
                let productoModificado = {
                    id: id,
                    cantidad: cantidad
                }

                this.arrayMotos.push(productoModificado)

                localStorage.setItem("array-motos", JSON.stringify(this.arrayMotos))
                localStorage.setItem("motos-carrito", JSON.stringify(this.productosDelCarrito))

                this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length
            } else {
                console.log("asdasd");
            }
        },

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

        plusCounter(){
            if (this.cantidad < this.bike.stock) {
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

            if (this.totalCarrito.length < this.productosGeneral) {
                this.totalCarrito.push(total)
            }
            
            if (this.totalCarrito.length <= this.productosGeneral) {
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