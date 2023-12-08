import './Contact.css';

const Contact = () => {
  
  const submitHandler = (e) => {
    e.preventDefault();
  };

  return (
    <div className='submit-form'>
      <form onSubmit={submitHandler}>
        <label htmlFor='name'>Име: 
          <input type="text" name="name"  />
        </label>
        <label htmlFor='surname'>Презиме: 
          <input type="text" name="surname" />
        </label>
        <label htmlFor='email'>Емаил: 
          <input type="email" name="email" />
        </label>
        <label htmlFor='message'>Порака: 
          <textarea name="message"></textarea>
        </label>

        <input type="submit" value="Прати" />
      </form>
    </div>
  );
}

export default Contact;