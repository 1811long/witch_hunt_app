package model.evenementPartie;

/**
 * Cette classe représente les messages que le modèle va envoyer à la vue pour qu'elle puisse savoir quelle partie de
 * la vue à mettre à jour
 */
public class ObjectMessage {
    /**
     * Représenter les évènements qui peuvent se produire dans les modèles, la liste des évènements
     * est présenté dans la class EventType
     */
    private String eventType;

    /**
     * Représenter un message à la vue de l'Application
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
     * @return l'évènement qui est associé à cet objet
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
