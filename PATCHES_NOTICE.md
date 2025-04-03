# Patches

Patches that Mindustry Hotfix has that you may not want.

## Removal of UUIDs in trace info

Admin trace info does not report UUIDs for security purposes as there were often
cases of admins abusing this.

Can be reverted by overriding admin events handler via a plugin.

## Splash pierce now respects building damage multiplier

Most likely this will not be a problem for you, but it may break the balance of mods
that rely on this (idk of any examples, I don't play modded).

Can be reverted by setting `hfPierceIgnoresBuildingDamageMultiplier` to `true` via a
plugin.
