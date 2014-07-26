package io.memento.infra.util;

import com.google.common.collect.Lists;
import io.memento.domain.model.Bookmark;
import io.memento.domain.model.Note;
import io.memento.infra.repository.bookmark.BookmarkRepository;
import io.memento.infra.repository.note.NoteRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class DataGenerator {

    @Inject
    private BookmarkRepository bookmarkRepository;

    @Inject
    private NoteRepository noteRepository;

    private static Logger logger = LoggerFactory.getLogger(DataGenerator.class);

    public void generateData() {
        generateNotes();
        generateBookmarks();
    }

    /*
     * Notes
     */

    public void generateNotes() {
        List<Note> notes = Lists.newArrayList();
        notes.add(newNote("[Note] [CNP] [IQS] Choses à faire", "", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "2014-07-22"));
        notes.add(newNote("[Note] Conflans Sainte-Honorine", "", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "2014-01-01"));
        notes.add(newNote("[Note] [ING] [HomeLoan] [Simulateurs] Brouillon de mail", "", "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.", "2013-01-01"));
        noteRepository.save(notes);
    }

    private Note newNote(String title, String description, String content, String date) {
        Note note = Note.create(title, description, content);
        if (date != null) {
            DateTime time = new DateTime(date);
            note.setCreationDate(time);
        } else {
            note.setCreationDate(new DateTime());
        }
        return note;
    }

    /*
     * Bookmarks
     */

    public void generateBookmarks() {
        List<Bookmark> bookmarks = Lists.newArrayList();

        bookmarks.add(newBookmark("[Bookmark] Agile Spirit", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "http://agile-spirit.fr", "2014-07-21"));
        bookmarks.add(newBookmark("[Bookmark] OCTO Technology", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "http://octo.com", "2014-07-14"));
        bookmarks.add(newBookmark("[Bookmark] Google Search Engine", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "http://google.com", "2014-07-01"));
        bookmarks.add(newBookmark("[Bookmark] Amazon e-commerce", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "http://amazon.com", "2014-06-10"));
        bookmarks.add(newBookmark("[Bookmark] Facebook - Social network", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.", "http://facebook.com", "2014-01-01"));
        bookmarks.add(newBookmark("[Bookmark] Twitter - Social microblogging platform", "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.", "http://twitter.com", "2013-12-29"));
        bookmarks.add(newBookmark("[Bookmark] LinkedIn", "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.", "http://linkedin.com", "2013-11-21"));
        bookmarks.add(newBookmark("[Bookmark] Ebay", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.", "http://ebay.com", "2010-05-20"));
/*
        for (int i = 1 ; i < 100 ; i++) {
            bookmarks.add(newBookmark("Bookmark-" + i++, "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "http://amazon.com", ));
            bookmarks.add(newBookmark("Bookmark-" + i++, "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.", "http://facebook.com"));
            bookmarks.add(newBookmark("Bookmark-" + i++, "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.", "http://twitter.com"));
            bookmarks.add(newBookmark("Bookmark-" + i++, "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.", "http://linkedin.com"));
            bookmarks.add(newBookmark("Bookmark-" + i++, "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.", "http://ebay.com"));
        }
*/

        bookmarkRepository.save(bookmarks);
    }

    private Bookmark newBookmark(String title, String description, String url, String date) {
        Bookmark bookmark = Bookmark.create(title, description, url);
        if (date != null) {
            DateTime time = new DateTime(date);
            bookmark.setCreationDate(time);
        } else {
            bookmark.setCreationDate(new DateTime());
        }
        return bookmark;
    }

    public void retrieveAndDisplayAllData() {
        displayData(bookmarkRepository.findAll());
    }

    public void retrieveAndDisplaySortedData() {
        displayData(bookmarkRepository.findLastBookmarksOrderByCreationDateDesc(3));
    }

    private void displayData(Iterable<Bookmark> items) {
        List<Bookmark> bookmarks = Lists.newArrayList(items);
        if (bookmarks.isEmpty()) {
            logger.info("There is no data");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n\"bookmarks\" : {\r\n");
            for (Bookmark bookmark : bookmarks) {
                sb.append("  {\r\n");
                sb.append("    \"id\" : \"" + bookmark.getId() + "\",\r\n");
                sb.append("    \"url\" : \"" + bookmark.getUrl() + "\",\r\n");
                sb.append("    \"title\" : \"" + bookmark.getTitle() + "\",\r\n");
                sb.append("    \"description\" : \"" + bookmark.getDescription() + "\",\r\n");
                sb.append("    \"creationDate\" : \"" + bookmark.getCreationDate() + "\",\r\n");
                sb.append("    \"modificationDate\" : \"" + bookmark.getModificationDate() + "\"\r\n");
                sb.append("  }\r\n");
            }
            sb.append("}");
            logger.info(sb.toString());
        }
    }

}
