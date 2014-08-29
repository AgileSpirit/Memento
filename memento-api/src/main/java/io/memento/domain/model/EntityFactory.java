package io.memento.domain.model;

import org.joda.time.DateTime;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    23/08/2014
 */
public class EntityFactory {

    /*
     * Account
     */

    public static Account newAccount(String clientId, IdentityProvider provider) {
        Account account = new Account();
        account.setClientId(clientId);
        account.setProvider(provider);
        account.setCreationDate(new DateTime());
        return account;
    }

    public static Account newAccount(String clientId, IdentityProvider provider, String firstName, String lastName) {
        Account account = newAccount(clientId, provider);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        return account;
    }

    /*
     * Bookmark
     */

    public static Bookmark newBookmark(Account owner, String url, String title, String description) {
        Bookmark bookmark = new Bookmark();
        bookmark.setOwner(owner);
        bookmark.setTitle(title);
        bookmark.setDescription(description);
        bookmark.setUrl(url);
        return bookmark;
    }

    public static Bookmark newBookmark(Account owner, String url, String title, String description, String date) {
        Bookmark bookmark = EntityFactory.newBookmark(owner, url, title, description);
        if (date != null) {
            DateTime time = new DateTime(date);
            bookmark.setCreationDate(time);
        } else {
            bookmark.setCreationDate(new DateTime());
        }
        return bookmark;
    }

    /*
     * Note
     */

    public static Note newNote(Account owner, String title, String content) {
        Note note = new Note();
        note.setOwner(owner);
        note.setTitle(title);
        note.setContent(content);
        return note;
    }

    public static Note newNote(Account owner, String title, String content, String date) {
        Note note = EntityFactory.newNote(owner, title, content);
        if (date != null) {
            DateTime time = new DateTime(date);
            note.setCreationDate(time);
        } else {
            note.setCreationDate(new DateTime());
        }
        return note;
    }


}
