const {
    createApp
} = Vue


const urlParams = new URLSearchParams(window.location.search);
const getID = urlParams.get('id');


createApp({
    data() {
        return {
            message: 'Hello Vue!',
            carouselValue: 0,
            bike: {},
            images: [],
            scrolled: false,
            cantidad: 1,
        }
    },

    created() {
        axios.get(`/api/motorcycles/${getID}`)
            .then(data => {
                this.bike = data.data
                this.images.push(this.bike.images)
                console.log(this.bike)
                console.log(this.images)
            })


    },

    methods: {
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
    },

    computed: {

    }
}).mount('#app')



var nums = [145, 85, 99, 54, 04, 83]

var res = this.nums.reduce((acc, actual, indice, arr) => {
    return acc += actual
}, 0);
console.log(res)