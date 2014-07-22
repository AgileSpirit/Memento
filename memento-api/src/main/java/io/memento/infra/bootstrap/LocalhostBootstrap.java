package io.memento.infra.bootstrap;

import com.google.common.collect.Lists;
import io.memento.domain.model.Bookmark;
import io.memento.infra.mailing.MailService;
import io.memento.infra.repository.BookmarkRepository;
import io.memento.infra.util.DataGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@Profile("localhost")
public class LocalhostBootstrap extends ApplicationBootstrap {

    @Inject
    private DataGenerator dataGenerator;
    @Inject
    private MailService mailService;
    @Inject
    private BookmarkRepository bookmarkRepository;
    @Value("${mail.message.to}")
    private String mailTo;

    @Override
    void bootstrap() {
        dataGenerator.generateData();

        /* Uncomment the instruction below to display the populated bookmarks */
        // dataGenerator.retrieveAndDisplayAllData();

        /* Uncomment the instruction below to test mail sending after io.memento.application bootstraping. */
        // sendTestMail();
    }

    private void sendTestMail() {
        // Retrieve bookmarks
        final List<Bookmark> bookmarks = Lists.newArrayList(bookmarkRepository.findAll());

        // Prepare mail content data model
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("bookmarks", bookmarks);

        // Send mail
        mailService.sendMail("JRocket test", mailTo, "sendBookmarks.vm", model);
    }

}
