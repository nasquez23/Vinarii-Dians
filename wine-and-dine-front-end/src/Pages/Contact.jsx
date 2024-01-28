import "./Contact.css";

const Contact = () => {
  return (
    // Форма за контакт со емаил адреса за испраќање на порака
    <div className="submit-form">
      <h1>Контакт</h1>
      <form
        id="mail_form"
        action="mailto:winendine@gmail.com"
        method="get"
        enctype="text/plain"
        target="_blank"
      >
        <label htmlFor="subject">Наслов</label>
        <input
          type="text"
          id="subject"
          name="subject"
          placeholder="Внесете ја целта на пораката..."
        />
        <label htmlFor="body">
          Порака:
          <textarea
            type="text"
            name="body"
            id="body"
            placeholder="Внесете ја вашата порака..."
          ></textarea>
        </label>
        <input type="submit" value="Испрати порака" />
      </form>
    </div>
  );
};

export default Contact;
