package yogi.base.relationship;

import java.util.Collection;

import yogi.base.relationship.types.OneToManyInverseRelationship;

public abstract class SpecialRelationshipManager<T> extends BaseRelationshipManager<T>{

	protected SpecialRelationshipManager() {
		super();
	}
	
   protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> void buildRelationship(F from, Collection<T> toObjects, R oneToManyInverseRelationship)
   {
	   from.getRelationshipController().addAllToRelationship(oneToManyInverseRelationship, toObjects);
   }
   
   protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> void deleteRelationship(F from, Collection<T> toObjects, R OneToManyInverseRelationship)
   {
	   from.getRelationshipController().removeAllFromRelationship(OneToManyInverseRelationship, toObjects);
   }
   
   protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> void deleteRelationship(F from, R OneToManyInverseRelationship)
   {
	   from.getRelationshipController().deleteOneToManyRelationship(OneToManyInverseRelationship);
   }
   
   protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> void clearRelationship(F from, R OneToManyInverseRelationship)
   {
	   from.getRelationshipController().clearOneToManyRelationship(OneToManyInverseRelationship);
   }
}
