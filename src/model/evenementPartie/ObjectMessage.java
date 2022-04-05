package model.evenementPartie;

/**
 * Cette classe repr�sente les messages que le mod�le va envoyer � la vue pour qu'elle puisse savoir quelle partie de
 * la vue � mettre � jour
 */
public class ObjectMessage {
    /**
     * Repr�senter les �v�nements qui peuvent se produire dans les mod�les, la liste des �v�nements
     * est pr�sent� dans la class EventType
     */
    private String eventType;

    /**
     * Repr�senter un message � la vue de l'Application
     */
    private String message;

    /**
     * Constructor de l'ObjectMessage
     * @param eventType
     * @param message
     */
    public ObjectMessage(String eventType, String message){
        this.eventType = eventType;
        this.message = message;
    }

    /**
     * Constructor de l'ObjectMessage
     * @param eventType
     */
    public ObjectMessage(String eventType){
        this.eventType = eventType;
    }

    /**
     * Getter d'eventType
     * @return l'�v�nement qui est associ� � cet objet
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Setter d'eventType
     * @param eventType
     * @return void
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    /**
     * Getter de message
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter de message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
