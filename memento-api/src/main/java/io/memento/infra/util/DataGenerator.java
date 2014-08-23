package io.memento.infra.util;

import com.google.common.collect.Lists;
import io.memento.domain.model.Account;
import io.memento.domain.model.Bookmark;
import io.memento.domain.model.EntityFactory;
import io.memento.domain.model.Note;
import io.memento.infra.authentication.IdentityProvider;
import io.memento.infra.repository.bookmark.BookmarkRepository;
import io.memento.infra.repository.note.NoteRepository;
import io.memento.infra.repository.user.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class DataGenerator {

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private BookmarkRepository bookmarkRepository;

    @Inject
    private NoteRepository noteRepository;

    private static Logger logger = LoggerFactory.getLogger(DataGenerator.class);

    public void generateData() {
        generateAccounts();
    }

    /*
     * Accounts
     */

    private void generateAccounts() {
        List<Account> accounts = Lists.newArrayList();
        accounts.add(EntityFactory.newAccount("0123-4567-89AB", IdentityProvider.GOOGLE));
        accounts.add(EntityFactory.newAccount("CDEF-GHIJ-KLMN", IdentityProvider.FACEBOOK));
        accounts.add(EntityFactory.newAccount("OPQR-STUV-WXYZ", IdentityProvider.TWITTER));
        accountRepository.save(accounts);

        Account owner = EntityFactory.newAccount("jdoe", IdentityProvider.GOOGLE, "John", "Doe");
        owner = accountRepository.save(owner);

        generateNotes(owner);
        generateBookmarks(owner);
    }

    /*
     * Notes
     */

    public void generateNotes(Account owner) {
        List<Note> notes = Lists.newArrayList();
        notes.add(EntityFactory.newNote(owner, "[Note] [CNP] [IQS] Choses à faire", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "2014-07-22"));
        notes.add(EntityFactory.newNote(owner, "[Note] Conflans Sainte-Honorine", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "2014-01-01"));
        notes.add(EntityFactory.newNote(owner, "[Note] [ING] [HomeLoan] [Simulateurs] Brouillon de mail", "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.", "2013-01-01"));
        noteRepository.save(notes);
    }

    /*
     * Bookmarks
     */

    public void generateBookmarks(Account owner) {
        List<Bookmark> bookmarks = Lists.newArrayList();

        bookmarks.add(EntityFactory.newBookmark(owner, "[Bookmark] Agile Spirit", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "http://agile-spirit.fr", "2014-07-21"));
        bookmarks.add(EntityFactory.newBookmark(owner, "[Bookmark] OCTO Technology", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "http://octo.com", "2014-07-14"));
        bookmarks.add(EntityFactory.newBookmark(owner, "[Bookmark] Google Search Engine", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "http://google.com", "2014-07-01"));
        bookmarks.add(EntityFactory.newBookmark(owner, "[Bookmark] Amazon e-commerce", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "http://amazon.com", "2014-06-10"));
        bookmarks.add(EntityFactory.newBookmark(owner, "[Bookmark] Facebook - Social network", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.", "http://facebook.com", "2014-01-01"));
        bookmarks.add(EntityFactory.newBookmark(owner, "[Bookmark] Twitter - Social microblogging platform", "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.", "http://twitter.com", "2013-12-29"));
        bookmarks.add(EntityFactory.newBookmark(owner, "[Bookmark] LinkedIn", "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.", "http://linkedin.com", "2013-11-21"));
        bookmarks.add(EntityFactory.newBookmark(owner, "[Bookmark] Ebay", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.", "http://ebay.com", "2010-05-20"));
/*
        for (int i = 1 ; i < 100 ; i++) {
            bookmarks.add(EntityFactory.newBookmark(account, "Bookmark-" + i++, "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "http://amazon.com", ));
            bookmarks.add(EntityFactory.newBookmark(account, "Bookmark-" + i++, "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.", "http://facebook.com"));
            bookmarks.add(EntityFactory.newBookmark(account, "Bookmark-" + i++, "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.", "http://twitter.com"));
            bookmarks.add(EntityFactory.newBookmark(account, "Bookmark-" + i++, "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.", "http://linkedin.com"));
            bookmarks.add(EntityFactory.newBookmark(account, "Bookmark-" + i++, "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.", "http://ebay.com"));
        }
*/
        Bookmark bookmark = EntityFactory.newBookmark(owner, "Bonnes pratiques d'implémentation de services applicatifs", "toto", "http://blog.agile-spirit.fr/2014/05/27/bonnes-pratiques-d-implementation-de-services-applicatifs.html", "2014-08-21");
        bookmark.setContent("<div><div class=\"post-content\"><p>Les architectures &#xE0;base de services sont depuis longtemps monnaies courantes. Avec l'av&#xE8;nement du Web et la mont&#xE9;e en puissance des architectures REST et/ou orient&#xE9;es micro-services, i.e. les WOA (Web Oriented Architectures) ou m&#xEA;me du DDD (et sa couche \"application\"), les services applicatifs ont de beaux jours devant eux.</p><p>Une difficult&#xE9;, quand on &#xE9;crit de tels composants, est de d&#xE9;finir une strat&#xE9;gie Orient&#xE9;e Objet &#xE0;la fois simple, coh&#xE9;rente et applicable &#xE0;tous les services applicatifs de notre logiciel.</p><p>Je vous livre ci-dessous les bonnes pratiques que j'applique quand je dois concevoir et d&#xE9;velopper ce type de composants (services ESB/EAI, WS SOAP/XML, Resources REST, etc.).</p><h2>Le pattern Request / Resource / Response</h2><p>Dans les premiers temps du d&#xE9;veloppement de services applicatifs vou&#xE9;s &#xE0;&#xEA;tre consomm&#xE9;s par d'autres syst&#xE8;mes ou composants logiciels (ex : UI, WS d'un partenaire, autres jobs/agents internes), on a plut&#xF4;t tendance &#xE0;se concentrer sur les informations qui transitent (arguments en entr&#xE9;e et valeurs de retour) plut&#xF4;t que sur la fa&#xE7;on dont on les re&#xE7;oit / transmet. On cherche &#xE0;v&#xE9;rifier la m&#xE9;canisque et sa configuration, tout en privil&#xE9;giant une productivit&#xE9;&#xE9;lev&#xE9;e, sans fioriture, <em>straight-to-the-point</em>. </p><p>Puis vient un moment o&#xF9;l'on en vient &#xE0;s'interroger sur la gestion des erreurs. De m&#xEA;me, &#xE0;partir d'un certain stade la quantit&#xE9;d'arguments &#xE0;g&#xE9;rer devient probl&#xE9;matique.</p><p>C'est &#xE0;ce moment l&#xE0;qu'appara&#xEE;t g&#xE9;n&#xE9;ralement le pattern Request / Resource / Response. Autrement dit, pour un service donn&#xE9;e (ex : effectuer une recherche de produit), on va faire appel &#xE0;2 classes et la m&#xE9;thode-service.</p><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductSearchRequest.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductSearchRequest</span><span class=\"o\">{</span><span class=\"kd\">private</span><span class=\"n\">String</span><span class=\"n\">expression</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"kt\">int</span><span class=\"n\">offset</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"kt\">int</span><span class=\"n\">number</span><span class=\"o\">;</span><span class=\"o\">...</span><span class=\"o\">}</span></code></pre></div><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductSearchResponse.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductSearchResponse</span><span class=\"o\">{</span><span class=\"kd\">private</span><span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">ProductDto</span><span class=\"o\">&gt;</span><span class=\"n\">results</span><span class=\"o\">=</span><span class=\"k\">new</span><span class=\"n\">ArrayList</span><span class=\"o\">();</span><span class=\"kd\">private</span><span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">String</span><span class=\"o\">&gt;</span><span class=\"n\">errors</span><span class=\"o\">=</span><span class=\"k\">new</span><span class=\"n\">ArrayList</span><span class=\"o\">();</span><span class=\"o\">...</span><span class=\"o\">}</span></code></pre></div><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductResource.java */</span><span class=\"kd\">public</span><span class=\"kd\">interface</span><span class=\"nc\">ProductResource</span><span class=\"o\">{</span><span class=\"n\">ProductSearchResponse</span><span class=\"nf\">search</span><span class=\"o\">(</span><span class=\"n\">ProductSearchRequest</span><span class=\"n\">request</span><span class=\"o\">);</span><span class=\"o\">...</span><span class=\"o\">}</span></code></pre></div><p>L'inconv&#xE9;nient de cette fa&#xE7;on de faire est le nombre de classes &#xE0;&#xE9;crire n&#xE9;cessaires pour un service. C'est le prix &#xE0;payer pour assurer un d&#xE9;coupage en couche solide et de qualit&#xE9;.</p><h2>Responsabilit&#xE9;s Orient&#xE9;es Objet</h2><p>Une des plus grandes difficult&#xE9;s (pour moi, LA plus grande) dans la conception Orient&#xE9;e Objet est l'attribution des responsabilit&#xE9;s pour une classe ou type de classe. Avec le temps et apr&#xE8;s avoir tent&#xE9;plusieurs strat&#xE9;gies, je limite d&#xE9;sormais mes services applicatifs aux 4 attributions suivantes :</p><ul><li>Contr&#xF4;ler les param&#xE8;tres pass&#xE9;s en requ&#xEA;te</li><li>Assurer la transformation Objects de Transfert - Objets du Domaine</li><li>Ordonnancer le(s) service(s) m&#xE9;tier (et g&#xE9;rer les erreurs remont&#xE9;es)</li><li>Construire et retourner une r&#xE9;ponse valide</li></ul><h3>1/ Contr&#xF4;ler les param&#xE8;tres pass&#xE9;s en requ&#xEA;te</h3><p>Un service applicatif (qu'il soit Web ou pas) repr&#xE9;sente un point d'entr&#xE9;e dans le Syst&#xE8;me (d'o&#xF9;l'emploi de la terminologie <em>endpoint</em> dans les frameworks d&#xE9;di&#xE9;s). De fait, il repr&#xE9;sente une faille d'intrusion ou d'injection malicieuse potentielle. Il convient donc, &#xE0;ce niveau, de se montrer particuli&#xE8;rement prudent (voire m&#xE9;fiant, ou m&#xEA;me parano&#xEF;aque), <strong>m&#xEA;me quand le consommateur est un composant interne du SI de l'entreprise</strong>.</p><p>Une autre raison de v&#xE9;rifier les param&#xE8;tres est tout simplement que le consommateur peut en faire un mauvais usage, par m&#xE9;connaissance ou incompr&#xE9;hension. Il ne faudrait pas qu'un mauvais appel, avec de mauvais arguments (ou sans des param&#xE8;tres requis) mettent l'application dans un &#xE9;t&#xE2;t instable ou incoh&#xE9;rent.</p><p>Pour ces deux raisons, il convient syst&#xE9;matiquement de v&#xE9;rifier, en d&#xE9;but de chaque service, la <strong>validit&#xE9;technique</strong> des arguments de la requ&#xEA;te. </p><p>On parle ici de contr&#xF4;ler par exemple l'existence et/ou le type d'un champs, comme dans l'exemple ci-dessous. Il ne s'agit pas d'effectuer de contr&#xF4;le fonctionnel, relatifs aux valeurs des arguments, mais plut&#xF4;t des contr&#xF4;les sur le format des donn&#xE9;es en entr&#xE9;es. Les contr&#xF4;les m&#xE9;tier seront assum&#xE9;s par les services du domaine / m&#xE9;tier dont c'est l'une des responsabilit&#xE9;s.</p><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductResourceImpl.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductResourceImpl</span><span class=\"kd\">implements</span><span class=\"n\">ProductResource</span><span class=\"o\">{</span><span class=\"kd\">public</span><span class=\"n\">ProductSearchResponse</span><span class=\"nf\">search</span><span class=\"o\">(</span><span class=\"n\">ProductSearchRequest</span><span class=\"n\">request</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"n\">ProductSearchResponse</span><span class=\"n\">response</span><span class=\"o\">=</span><span class=\"k\">new</span><span class=\"n\">ProductSearchResponse</span><span class=\"o\">();</span><span class=\"c1\">// 1. Check Request Object</span><span class=\"k\">if</span><span class=\"o\">(!</span><span class=\"n\">checkRequest</span><span class=\"o\">(</span><span class=\"n\">request</span><span class=\"o\">,</span><span class=\"n\">response</span><span class=\"o\">))</span><span class=\"o\">{</span><span class=\"k\">return</span><span class=\"n\">response</span><span class=\"o\">;</span><span class=\"o\">}</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"kd\">private</span><span class=\"kt\">boolean</span><span class=\"nf\">checkRequest</span><span class=\"o\">(</span><span class=\"n\">ProductSearchRequest</span><span class=\"n\">request</span><span class=\"o\">,</span><span class=\"n\">ProductSearchResponse</span><span class=\"n\">response</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"k\">if</span><span class=\"o\">(</span><span class=\"n\">request</span><span class=\"o\">==</span><span class=\"kc\">null</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"n\">reponse</span><span class=\"o\">.</span><span class=\"na\">putError</span><span class=\"o\">(</span><span class=\"s\">\"The request received was null\"</span><span class=\"o\">);</span><span class=\"k\">return</span><span class=\"kc\">false</span><span class=\"o\">;</span><span class=\"o\">}</span><span class=\"k\">if</span><span class=\"o\">(</span><span class=\"n\">request</span><span class=\"o\">.</span><span class=\"na\">getExpression</span><span class=\"o\">()</span><span class=\"o\">==</span><span class=\"kc\">null</span><span class=\"o\">||</span><span class=\"n\">request</span><span class=\"o\">.</span><span class=\"na\">getExpression</span><span class=\"o\">().</span><span class=\"na\">isEmpty</span><span class=\"o\">())</span><span class=\"o\">{</span><span class=\"n\">reponse</span><span class=\"o\">.</span><span class=\"na\">putError</span><span class=\"o\">(</span><span class=\"s\">\"Search expression is required\"</span><span class=\"o\">);</span><span class=\"k\">return</span><span class=\"kc\">false</span><span class=\"o\">;</span><span class=\"o\">}</span><span class=\"o\">...</span><span class=\"k\">return</span><span class=\"kc\">true</span><span class=\"o\">;</span><span class=\"o\">}</span><span class=\"o\">}</span></code></pre></div><h3>2/ Assurer la transformation Objects de Transfert - Objets du Domaine</h3><p>Un pattern que l'on retrouve souvent en programmation de services applicatifs est l'emploi de DTO (Data Transfer Objects). Ces objects sont g&#xE9;n&#xE9;ralement des classes de style POJO, avec extr&#xE8;mement peu de comportement (le max &#xE9;tant g&#xE9;n&#xE9;ralement des capacit&#xE9;s de pagination pour des DTO ayant trait &#xE0;de la recherche).</p><p>Quand c'est le cas, alors il devient de la responsabilit&#xE9;de la couche applicative de convertir les DTO contenus dans l'objet Request en objets exploitables par la couche m&#xE9;tier (les diff&#xE9;rents <em>Building Blocks</em> de DDD, i.e. Domain Entities, Value Objects, Agreggates and Roots, ou tout simplement les Entit&#xE9;s JPA associ&#xE9;es).</p><p>Evidemment le service doit aussi assurer la transformation inverse si n&#xE9;cessaire, depuis les objets m&#xE9;tier vers les objets de transfert.</p><p>On peut s'appuyer sur des patterns tels que Factory Method ou Builder pour r&#xE9;aliser les transformations.</p><p>Ou alors utiliser des frameworks tels que <a href=\"http://dozer.sourceforge.net/\" title=\"Dozer\">Dozer</a>, <a href=\"https://code.google.com/p/objenesis/\" title=\"Objenesis\">Objenesis</a>, ou <a href=\"https://code.google.com/p/orika/\" title=\"Orika\">Orika</a> en Java.</p><p>Finalement, on peut aussi tr&#xE8;s bien le faire &#xE0;la main si le mapping est simple, comme dans l'exemple ci-desous :</p><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductResourceImpl.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductResourceImpl</span><span class=\"kd\">implements</span><span class=\"n\">ProductResource</span><span class=\"o\">{</span><span class=\"kd\">public</span><span class=\"n\">ProductSearchResponse</span><span class=\"nf\">search</span><span class=\"o\">(</span><span class=\"n\">ProductSearchRequest</span><span class=\"n\">request</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"o\">...</span><span class=\"c1\">// 2. a) Convert Request DTO to Domain Entity</span><span class=\"n\">ProductSearchCriteria</span><span class=\"n\">criteria</span><span class=\"o\">=</span><span class=\"n\">convert</span><span class=\"o\">(</span><span class=\"n\">request</span><span class=\"o\">);</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"kd\">private</span><span class=\"n\">ProductSearchCriteria</span><span class=\"nf\">convert</span><span class=\"o\">(</span><span class=\"n\">ProductSearchRequest</span><span class=\"n\">request</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"n\">ProductSearchCriteria</span><span class=\"n\">criteria</span><span class=\"o\">=</span><span class=\"k\">new</span><span class=\"n\">ProductSearchCriteria</span><span class=\"o\">();</span><span class=\"n\">criteria</span><span class=\"o\">.</span><span class=\"na\">setExpression</span><span class=\"o\">(</span><span class=\"n\">request</span><span class=\"o\">.</span><span class=\"na\">getExpression</span><span class=\"o\">());</span><span class=\"n\">criteria</span><span class=\"o\">.</span><span class=\"na\">setOffset</span><span class=\"o\">(</span><span class=\"n\">request</span><span class=\"o\">.</span><span class=\"na\">getOffset</span><span class=\"o\">());</span><span class=\"n\">criteria</span><span class=\"o\">.</span><span class=\"na\">setNumRows</span><span class=\"o\">(</span><span class=\"n\">request</span><span class=\"o\">.</span><span class=\"na\">getNumber</span><span class=\"o\">());</span><span class=\"k\">return</span><span class=\"n\">criteria</span><span class=\"o\">;</span><span class=\"o\">}</span><span class=\"o\">}</span></code></pre></div><h3>3/ Ordonnancer le(s) service(s) m&#xE9;tier (et g&#xE9;rer les erreurs remont&#xE9;es)</h3><p>Une fois qu'on s'est assur&#xE9;que la requ&#xEA;te re&#xE7;ue est bien form&#xE9;e et qu'on l'a convertie en objet(s) exploitable(s), on peut enfin appeler le ou les service(s) m&#xE9;tiers en charge de la logique fonctionnelle de l'application, <strong>dans le bon ordre</strong>.</p><p>Il s'agit l&#xE0;de la seule intelligence v&#xE9;ritable de la couche applicative. Et encore, la plupart du temps, les services applicatifs sont des passe-plat vers un unique service business sous-jacent.</p><p>Dans notre exemple, une fois que la recherche a eu lieue, on veut l'historiser (pour des raisons l&#xE9;gales, ou pour am&#xE9;liorer la connaissance de sa base utilisateurs) dans un log. On veut aussi intercepter les erreurs, si le service de recherche en renvoie, pour pouvoir les remonter &#xE0;l'utilisateur.</p><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductResourceImpl.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductResourceImpl</span><span class=\"kd\">implements</span><span class=\"n\">ProductResource</span><span class=\"o\">{</span><span class=\"kd\">public</span><span class=\"n\">ProductSearchResponse</span><span class=\"nf\">search</span><span class=\"o\">(</span><span class=\"n\">ProductSearchRequest</span><span class=\"n\">request</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"o\">...</span><span class=\"c1\">// 3. Call ordered business services</span><span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">Product</span><span class=\"o\">&gt;</span><span class=\"n\">products</span><span class=\"o\">=</span><span class=\"kc\">null</span><span class=\"o\">;</span><span class=\"k\">try</span><span class=\"o\">{</span><span class=\"c1\">// a) Do search</span><span class=\"n\">products</span><span class=\"o\">=</span><span class=\"n\">productService</span><span class=\"o\">.</span><span class=\"na\">find</span><span class=\"o\">(</span><span class=\"n\">criteria</span><span class=\"o\">);</span><span class=\"k\">if</span><span class=\"o\">(</span><span class=\"n\">products</span><span class=\"o\">==</span><span class=\"kc\">null</span><span class=\"o\">||</span><span class=\"n\">products</span><span class=\"o\">.</span><span class=\"na\">isEmpty</span><span class=\"o\">())</span><span class=\"o\">{</span><span class=\"n\">response</span><span class=\"o\">.</span><span class=\"na\">putError</span><span class=\"o\">(</span><span class=\"s\">\"No product found\"</span><span class=\"o\">);</span><span class=\"o\">}</span><span class=\"n\">response</span><span class=\"o\">.</span><span class=\"na\">setResults</span><span class=\"o\">(</span><span class=\"n\">products</span><span class=\"o\">);</span><span class=\"o\">}</span><span class=\"k\">catch</span><span class=\"o\">(</span><span class=\"n\">DomainException</span><span class=\"n\">de</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"n\">response</span><span class=\"o\">.</span><span class=\"na\">putError</span><span class=\"o\">(</span><span class=\"s\">\"An erreur occured during search\"</span><span class=\"o\">);</span><span class=\"o\">}</span><span class=\"k\">finally</span><span class=\"o\">{</span><span class=\"c1\">// b) Log search</span><span class=\"n\">historyService</span><span class=\"o\">.</span><span class=\"na\">addEntry</span><span class=\"o\">(</span><span class=\"n\">criteria</span><span class=\"o\">,</span><span class=\"n\">products</span><span class=\"o\">);</span><span class=\"o\">}</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"o\">}</span></code></pre></div><h3>4/ Construire et retourner une r&#xE9;ponse valide</h3><p>Si besoin, il peut &#xEA;tre utile ou n&#xE9;cessaire &#xE0;ce stade d'ex&#xE9;cuter un second jeu de v&#xE9;rifications, histoire de garantir que ce qui sort du syst&#xE8;me est fiable, s&#xE9;curis&#xE9;, optimis&#xE9;, etc. Le but est de renvoyer un objet r&#xE9;ponse exploitable par TOUS les consommateurs du service.</p><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductResourceImpl.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductResourceImpl</span><span class=\"kd\">implements</span><span class=\"n\">ProductResource</span><span class=\"o\">{</span><span class=\"kd\">public</span><span class=\"n\">ProductSearchResponse</span><span class=\"nf\">search</span><span class=\"o\">(</span><span class=\"n\">ProductSearchRequest</span><span class=\"n\">request</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"o\">...</span><span class=\"c1\">// 2. b) </span><span class=\"n\">reponse</span><span class=\"o\">.</span><span class=\"na\">setResults</span><span class=\"o\">(</span><span class=\"n\">convert</span><span class=\"o\">(</span><span class=\"n\">products</span><span class=\"o\">));</span><span class=\"c1\">// 4. </span><span class=\"n\">a</span><span class=\"o\">)</span><span class=\"n\">Verify</span><span class=\"n\">reponse</span><span class=\"k\">if</span><span class=\"o\">(!</span><span class=\"n\">checkResponse</span><span class=\"o\">(</span><span class=\"n\">response</span><span class=\"o\">))</span><span class=\"o\">{</span><span class=\"k\">throw</span><span class=\"k\">new</span><span class=\"nf\">ApplicationException</span><span class=\"o\">();</span><span class=\"o\">}</span><span class=\"c1\">// b)</span><span class=\"k\">return</span><span class=\"n\">response</span><span class=\"o\">;</span><span class=\"o\">}</span><span class=\"o\">...</span><span class=\"cm\">/** Convert a list of Product entities into a list of Product DTO */</span><span class=\"kd\">private</span><span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">ProductDto</span><span class=\"o\">&gt;</span><span class=\"nf\">convert</span><span class=\"o\">(</span><span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">Product</span><span class=\"o\">&gt;</span><span class=\"n\">products</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"cm\">/** Verify if the built response if valid */</span><span class=\"kd\">private</span><span class=\"kt\">boolean</span><span class=\"nf\">checkResponse</span><span class=\"o\">(</span><span class=\"n\">ProductSearchResponse</span><span class=\"n\">response</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"o\">}</span></code></pre></div><h2>Le pattern Summary/Detail</h2><p>L'un des avantages d'une couche de services applicatifs est la rationalisation des services expos&#xE9;s. Une difficult&#xE9;qui peut appara&#xEE;tre est lorsque les syst&#xE8;mes consommateurs ont des contraintes (performances / bande passante) ou des usages de donn&#xE9;es tr&#xE8;s diff&#xE9;rents, ou inconciliable.</p><p>C'est le cas par exemple des applications mobiles. Quand un utilisateur navigue sur Internet depuis une station Desktop, il privil&#xE9;giera davantage la richesse du contenu et des interactions. Quand il acc&#xE8;de &#xE0;la m&#xEA;me application depuis un terminal mobile, il aura tendance &#xE0;&#xEA;tre plus attentif aux temps de r&#xE9;ponse (affichage, traitements et comportements) et &#xE0;la qualit&#xE9;de la lecture et de la navigation.</p><p>Une approche efficace est l'utilisation du pattern Summary/Detail qui consiste &#xE0;d&#xE9;finir le DTO retourn&#xE9;comme un agr&#xE9;gat de plusieurs \"sections de donn&#xE9;es\" :</p><ul><li><em>Summary</em> : contient les informations principalesvou&#xE9;es &#xE0;&#xEA;tre consomm&#xE9;es et utilis&#xE9;es par tous les clients</li><li><em>Detail</em> : contient des informations lourdes ou compl&#xE9;mentaires, utilis&#xE9;es par les clients qui en ont les moyens (ex: Desktop)</li></ul><p>Si besoin, il est envisageable de d&#xE9;finir d'autres sections pour avoir un d&#xE9;coupage encore plus fin (ex : Desktop, Tablet, SmartPhone). On peut par exemple consid&#xE9;rer le d&#xE9;coupage suivant : IdentityDto, SummaryDto, DetailDto.</p><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductDto.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductDto</span><span class=\"o\">{</span><span class=\"kd\">private</span><span class=\"n\">ProductSummaryDto</span><span class=\"n\">summary</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"n\">ProductDetailDto</span><span class=\"n\">detail</span><span class=\"o\">;</span><span class=\"o\">...</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductSummaryDto</span><span class=\"o\">{</span><span class=\"kd\">private</span><span class=\"n\">Long</span><span class=\"n\">id</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"n\">String</span><span class=\"n\">title</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"n\">String</span><span class=\"n\">self</span><span class=\"o\">;</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductDetailDto</span><span class=\"o\">{</span><span class=\"kd\">private</span><span class=\"kt\">byte</span><span class=\"o\">[]</span><span class=\"n\">image</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"n\">String</span><span class=\"n\">description</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"n\">SocialDataDto</span><span class=\"n\">socialData</span><span class=\"o\">;</span><span class=\"c1\">// Etc.</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"o\">}</span></code></pre></div><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductSearchRequest.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductSearchRequest</span><span class=\"o\">{</span><span class=\"kd\">private</span><span class=\"n\">String</span><span class=\"n\">expression</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"kt\">int</span><span class=\"n\">offset</span><span class=\"o\">;</span><span class=\"kd\">private</span><span class=\"kt\">int</span><span class=\"n\">number</span><span class=\"o\">;</span><span class=\"c1\">// Flag used to tell if we ask for a complete ProductDto, or only for its summary.</span><span class=\"kd\">private</span><span class=\"kt\">boolean</span><span class=\"n\">detailRequired</span><span class=\"o\">=</span><span class=\"kc\">false</span><span class=\"o\">;</span><span class=\"o\">...</span><span class=\"o\">}</span></code></pre></div><div class=\"highlight\"><pre><code class=\"java\"><span class=\"cm\">/** ProductResourceImpl.java */</span><span class=\"kd\">public</span><span class=\"kd\">class</span><span class=\"nc\">ProductResourceImpl</span><span class=\"kd\">implements</span><span class=\"n\">ProductResource</span><span class=\"o\">{</span><span class=\"kd\">public</span><span class=\"n\">ProductSearchResponse</span><span class=\"nf\">search</span><span class=\"o\">(</span><span class=\"n\">ProductSearchRequest</span><span class=\"n\">request</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"o\">...</span><span class=\"n\">reponse</span><span class=\"o\">.</span><span class=\"na\">setResults</span><span class=\"o\">(</span><span class=\"n\">convert</span><span class=\"o\">(</span><span class=\"n\">products</span><span class=\"o\">));</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"c1\">// Change conversion methods in order to take into account Summary/Detail pattern </span><span class=\"kd\">private</span><span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">ProductDto</span><span class=\"o\">&gt;</span><span class=\"nf\">convert</span><span class=\"o\">(</span><span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">Product</span><span class=\"o\">&gt;</span><span class=\"n\">products</span><span class=\"o\">,</span><span class=\"kt\">boolean</span><span class=\"n\">detailRequired</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">ProductDto</span><span class=\"o\">&gt;</span><span class=\"n\">results</span><span class=\"o\">=</span><span class=\"k\">new</span><span class=\"n\">ArrayList</span><span class=\"o\">&lt;&gt;();</span><span class=\"k\">for</span><span class=\"o\">(</span><span class=\"n\">Product</span><span class=\"n\">entity</span><span class=\"o\">:</span><span class=\"n\">products</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"n\">ProductDto</span><span class=\"n\">product</span><span class=\"o\">=</span><span class=\"k\">new</span><span class=\"n\">ProductDto</span><span class=\"o\">();</span><span class=\"n\">product</span><span class=\"o\">.</span><span class=\"na\">summary</span><span class=\"o\">=</span><span class=\"n\">convert</span><span class=\"o\">(</span><span class=\"n\">entity</span><span class=\"o\">);</span><span class=\"k\">if</span><span class=\"o\">(</span><span class=\"n\">detailRequired</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"n\">product</span><span class=\"o\">.</span><span class=\"na\">detail</span><span class=\"o\">=</span><span class=\"n\">convert</span><span class=\"o\">(</span><span class=\"n\">entity</span><span class=\"o\">);</span><span class=\"o\">}</span><span class=\"n\">results</span><span class=\"o\">.</span><span class=\"na\">add</span><span class=\"o\">(</span><span class=\"n\">product</span><span class=\"o\">);</span><span class=\"o\">}</span><span class=\"k\">return</span><span class=\"n\">results</span><span class=\"o\">;</span><span class=\"o\">}</span><span class=\"c1\">// Build a ProductSummaryDto from a Product entity</span><span class=\"kd\">private</span><span class=\"n\">ProductSummaryDto</span><span class=\"nf\">convert</span><span class=\"o\">(</span><span class=\"n\">Product</span><span class=\"n\">product</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"c1\">// Build a ProductDetailDto from a Product entity</span><span class=\"kd\">private</span><span class=\"n\">ProductDetailDto</span><span class=\"nf\">convert</span><span class=\"o\">(</span><span class=\"n\">Product</span><span class=\"n\">product</span><span class=\"o\">)</span><span class=\"o\">{</span><span class=\"o\">...</span><span class=\"o\">}</span><span class=\"o\">}</span></code></pre></div></div></div>");
        //bookmarks.add(bookmark);

        bookmarkRepository.save(bookmarks);
    }

    /*
     * UTILS
     */

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
