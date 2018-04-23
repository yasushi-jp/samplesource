package knowledgebank.service;

import knowledgebank.entity.KnowledgeComment;

/**
 *
 */
public interface Notifier {
    
    public void send(long knowledgeId, KnowledgeComment comment);
    
}
