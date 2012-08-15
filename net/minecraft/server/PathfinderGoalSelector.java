package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathfinderGoalSelector
{
	private List a = new ArrayList();
	private List b = new ArrayList();
	private final MethodProfiler c;
	private int d = 0;
	private int e = 3;

	public PathfinderGoalSelector(MethodProfiler paramMethodProfiler) {
		this.c = paramMethodProfiler;
	}

	public void a(int paramInt, PathfinderGoal paramPathfinderGoal) {
		this.a.add(new PathfinderGoalSelectorItem(this, paramInt, paramPathfinderGoal));
	}

	public void a() {
		ArrayList localArrayList = new ArrayList();
		PathfinderGoalSelectorItem localPathfinderGoalSelectorItem;
		if (this.d++ % this.e == 0) {
			for (localIterator = this.a.iterator(); localIterator.hasNext(); ) { localPathfinderGoalSelectorItem = (PathfinderGoalSelectorItem)localIterator.next();
				boolean bool = this.b.contains(localPathfinderGoalSelectorItem);

				if (bool) {
					if ((!b(localPathfinderGoalSelectorItem)) || (!a(localPathfinderGoalSelectorItem))) {
						localPathfinderGoalSelectorItem.a.c();
						this.b.remove(localPathfinderGoalSelectorItem);
					}
				}

				if ((!b(localPathfinderGoalSelectorItem)) || (!localPathfinderGoalSelectorItem.a.a()))
				{
					continue;
				}
				localArrayList.add(localPathfinderGoalSelectorItem);
				this.b.add(localPathfinderGoalSelectorItem); }
		}
		else {
			localIterator = this.b.iterator();

			while (localIterator.hasNext()) {
				localPathfinderGoalSelectorItem = (PathfinderGoalSelectorItem)localIterator.next();
				if (!localPathfinderGoalSelectorItem.a.b()) {
					localPathfinderGoalSelectorItem.a.c();
					localIterator.remove();
				}
			}
		}

		this.c.a("goalStart");

		for (Iterator localIterator = localArrayList.iterator(); localIterator.hasNext(); ) { localPathfinderGoalSelectorItem = (PathfinderGoalSelectorItem)localIterator.next();

			this.c.a(localPathfinderGoalSelectorItem.a.getClass().getSimpleName());
			localPathfinderGoalSelectorItem.a.e();
			this.c.b();
		}
		this.c.b();

		this.c.a("goalTick");

		for (localIterator = this.b.iterator(); localIterator.hasNext(); ) { localPathfinderGoalSelectorItem = (PathfinderGoalSelectorItem)localIterator.next();

			this.c.a(localPathfinderGoalSelectorItem.a.getClass().getSimpleName());
			localPathfinderGoalSelectorItem.a.d();
			this.c.b();
		}
		this.c.b();
	}

	private boolean a(PathfinderGoalSelectorItem paramPathfinderGoalSelectorItem) {
		this.c.a("canContinue");
		boolean bool = paramPathfinderGoalSelectorItem.a.b();
		this.c.b();
		return bool;
	}

	private boolean b(PathfinderGoalSelectorItem paramPathfinderGoalSelectorItem)
	{
		this.c.a("canUse");

		for (PathfinderGoalSelectorItem localPathfinderGoalSelectorItem : this.a) {
			if (localPathfinderGoalSelectorItem == paramPathfinderGoalSelectorItem)
				continue;
			if (paramPathfinderGoalSelectorItem.b >= localPathfinderGoalSelectorItem.b) {
				if ((this.b.contains(localPathfinderGoalSelectorItem)) && (!a(paramPathfinderGoalSelectorItem, localPathfinderGoalSelectorItem))) {
					this.c.b();
					return false;
				}
			} else if ((this.b.contains(localPathfinderGoalSelectorItem)) && (!localPathfinderGoalSelectorItem.a.g())) {
				this.c.b();
				return false;
			}
		}

		this.c.b();
		return true;
	}

	private boolean a(PathfinderGoalSelectorItem paramPathfinderGoalSelectorItem1, PathfinderGoalSelectorItem paramPathfinderGoalSelectorItem2) {
		return (paramPathfinderGoalSelectorItem1.a.h() & paramPathfinderGoalSelectorItem2.a.h()) == 0;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalSelector
 * JD-Core Version:		0.6.0
 */