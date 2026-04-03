import './styles/new-testimonial-container.css'
import Header from '../header/Header'
import Form from '../form/Form'

const NewTestimonialContainer = () => {
  return (
    <section className='new-testimonial'>
     <div className='new-testimonial-container'>
        <Header 
          title="Tu aprendizaje, en tus palabras" 
          subtitle='Cuentanos que lograste y como llegaste hasta aquí'
        />
        <Form/>
      </div>
    </section>
  )
}

export default NewTestimonialContainer