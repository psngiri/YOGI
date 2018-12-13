package yogi.base.relationship;

import yogi.base.Manager;
import yogi.base.Selector;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.OneToOneInverseRelationship;
import yogi.base.util.immutable.ImmutableList;

public abstract class BaseRelationshipManager<T> extends Manager<T>{

	protected BaseRelationshipManager() {
		super();
	}
	
   protected <F extends RelationshipObject, R extends OneToOneInverseRelationship<? super F,? super T>> void buildRelationship(F from, T to, R relationship)
   {
	   from.getRelationshipController().setOneToOneRelationship(relationship, to);
   }
   
   protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> void buildRelationship(F from, T to, R oneToManyInverseRelationship)
   {
	   from.getRelationshipController().addToRelationship(oneToManyInverseRelationship, to);
   }
   
   protected <F extends RelationshipObject, R extends OneToOneInverseRelationship<? super F,? super T>> void deleteRelationship(F from, T to, R relationship)
   {
	   from.getRelationshipController().deleteOneToOneRelationship(relationship);
   }
   
   protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> void deleteRelationship(F from, T to, R OneToManyInverseRelationship)
   {
	   from.getRelationshipController().removeFromRelationship(OneToManyInverseRelationship, to);
   }
   
   protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> void deleteRelationship(F from, R OneToManyInverseRelationship)
   {
	   from.getRelationshipController().deleteOneToManyRelationship(OneToManyInverseRelationship);
   }

   protected <F extends RelationshipObject, R extends OneToOneInverseRelationship<? super F,? super T>> T getRelationship(F from, R oneToOneInverseRelationship) {
		return  from.getRelationshipController().<T>getRelationship(oneToOneInverseRelationship);
	}

	protected <F extends RelationshipObject, R extends OneToOneInverseRelationship<? super F,? super T>> T getRelationship(F from, R oneToOneInverseRelationship, boolean inherited) {
		return  from.getRelationshipController().<T>getRelationship(oneToOneInverseRelationship, inherited);
	}

	protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> ImmutableList<T> getRelationship(F from, R oneToManyInverseRelationship) {
		return  from.getRelationshipController().getRelationship(oneToManyInverseRelationship);
	}

	protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> ImmutableList<T> getRelationship(F from, R oneToManyInverseRelationship, boolean inherited) {
		return  from.getRelationshipController().getRelationship(oneToManyInverseRelationship, inherited);
	}

   protected <F extends RelationshipObject, R extends OneToManyInverseRelationship<? super F,? super T>> void purgeRelationship(F from, Selector<T> toSelector, R OneToManyInverseRelationship)
   {
	   from.getRelationshipController().purgeRelationship(OneToManyInverseRelationship, toSelector);
   }
	   
}
