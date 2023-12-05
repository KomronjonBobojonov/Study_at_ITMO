cd ..
mkdir lab0
cd lab0/
mkdir dragonair0 lickilicky2 minccino8
touch baltoy4 klinklang8 shedinja1
cd dragonair0/
mkdir sewaddle infernape ivysaur
touch gigalith frillish herdier 
cd ..
cd lickilicky2/
mkdir gligar kakuna tangela oddish hippopotas
cd ..
cd minccino8/
mkdir quagsire
touch golett chandelure altaria

echo 'satk=4 sdef=7 spd=6' > baltoy4
echo -e "Способности Leech Life\nSand-Attack Fury Swipes Mind Reader Spite Confuse Ray Shadow Sneak
\nGrudge Heal Block Shadow Ball" > shedinja1
echo -e "Способности Charge Thundershock Gear Grind\nBind Charge Beam Autotomize Mirror Shot Screech Discharge Metal Sound \nShift Gear Lock-On Zap Cannon Hyper Beam" > klinklang8
cd dragonair0
echo -e "satk=6 sdef=7 \nspd=3" > gigalith
echo -e "weight=72.8 height=47.0 atk=4 def=5" > frillish
echo -e "satk=4 \nsdef=7 spd=6" > herdier
cd ..
cd minccino8
echo -e "Ходы Astonish \nDefense Curl Pound Mud-Slap Rollout Shadow Punch Iron Defense Mega" > golett
echo -e "Развитые способности Shadow \nTag" > chandelure
echo -e "satk=7 sdef=11 spd=8" > altaria 


chmod uo=r,g-rwx baltoy4
chmod ug=wx,o=rx dragonair0
cd dragonair0
chmod u=rx,go=x sewaddle
chmod u=wx,go=x infernape
chmod u-rwx,g=r,o=rw gigalith
chmod u-rwx,go=rw frillish
chmod u=rx,g=w,o=r ivysaur
chmod u-rwx,go=rw herdier
cd ..
chmod ug=rw,o=r klinklang8
chmod u=wx,g=wx,o=rx lickilicky2
cd lickilicky2
chmod u=rx,g=x,o=r gligar
chmod u=wx,g=rw,o=x kakuna
chmod u=rx,g=x,o=wx tangela
chmod u=wx,g=x,o=rwx oddish
chmod u=rx,go=rwx hippopotas
cd ..
chmod uo=rwx,g=wx minccino8
cd minccino8
chmod u-rwx,g=rw,o=w golett
chmod u=rw,g=w,o-rwx chandelure
chmod u=rx,g=rwx,o=wx quagsire
chmod u=rw,g=w,o=r altaria
cd ..
chmod uo=r,g-rwx shedinja1

ln -s shedinja1 dragonair0/frillishshedinja
ln shedinja1 dragonair0/frillishshedinja*
ln -s minccino8 Copy_33
chmod u+rwx dragonair0/gigalith
cat dragonair0/gigalith dragonair0/gigalith > baltoy4_84
cat baltoy4 > minccino8/altariabaltoy
chmod u+rwx lickilicky2
chmod u+rwx lickilicky2/gligar
chmod u+rwx lickilicky2/kakuna
chmod u+rwx lickilicky2/oddish
cp -a lickilicky2 lickilicky2/gligar
cat klinklang8 > lickilicky2/gligar/klinklang8
chmod u-rwx,g=r,o=rw dragonair0/gigalith
chmod u=wx,g=wx,o=rx lickilicky2
chmod u=rx,g=x,o=r lickilicky2/gligar
chmod u=wx,g=rw,o=x lickilicky2/kakuna
chmod u=wx,g=x,o=rwx lickilicky2/oddish

wc -m echo i* */i* */*/i* */*/*/i* | sort -k 1
ls -laR | grep '^-' | grep 'ga' | sort -k 9 2>/tmp/lab0errors
ls dragonair0/ | cat -n * 2>/dev/null | grep -v "d$"
ls -Rltuc | grep "^-" | grep "e$" | sort -r -k 8
ls -Rltuc | grep "^-" | grep "ga" | sort -r -k 8 | tail -n 4
cat dragonair0/herdier minccino8/golett minccino8/chandelure | cat -n | grep "h $"

rm -rf baltoy4
rm -rf minccino8/altaria
rm Copy_33
chmod u+rwx dragonair0
rm -rf dragonair0/frillishshedin*
chmod u+rwx minccino8
rm -rf minccino8
rm -rf dragonair0/sewaddle
chmod ug=wx,o=rx dragonair0