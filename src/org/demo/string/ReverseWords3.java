package org.demo.string;

/**
 * 反转字符串中的单词 III
 * https://leetcode.cn/leetbook/read/array-and-string/c8su7/
 * 
 * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 
 * 示例 1：
 * 输入：s = "Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * 
 * 示例 2:
 * 输入： s = "God Ding"
 * 输出："doG gniD"
 * 
 * 提示：
 * 1 <= s.length <= 5 * 104
 * s 包含可打印的 ASCII 字符。
 * s 不包含任何开头或结尾空格。
 * s 里 至少 有一个词。
 * s 中的所有单词都用一个空格隔开。
 */
public class ReverseWords3 {
	/*
	 * 执行用时：15 ms, 在所有 Java 提交中击败了16.71%的用户
	 * 内存消耗：43 MB, 在所有 Java 提交中击败了31.57%的用户
	 * 通过测试用例：29 / 29
	 */
	@Deprecated
	public String reverseWords3(String s) {
		StringBuilder reverse = null;
		StringBuilder temp = new StringBuilder();
		char c = ' ';
		for (int i = s.length() - 1; i >= 0; i--) {
			c = s.charAt(i);
			if (' ' == c) {
				if (null == reverse) {
					reverse = temp;
				} else {
					reverse = temp.append(' ').append(reverse);
				}
				temp = new StringBuilder();
			} else {
				temp.append(c);
			}
		}

		if (0 != temp.length()) {
			if (null == reverse) {
				reverse = temp;
			} else {
				reverse = temp.append(' ').append(reverse);
			}
		}
		return reverse.toString();
	}

	/*
	 * 执行用时：13 ms, 在所有 Java 提交中击败了21.02%的用户
	 * 内存消耗：43 MB, 在所有 Java 提交中击败了38.21%的用户
	 * 通过测试用例：29 / 29
	 */
	@Deprecated
	public String reverseWords2(String s) {
		StringBuilder builder = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		char c = ' ';
		for (int i = s.length() - 1; i >= 0; i--) {
			c = s.charAt(i);
			if (' ' == c) {

				builder.insert(0, temp).insert(0, ' ');
				temp = new StringBuilder();
			} else {
				temp.append(c);
			}
		}
		if (0 != temp.length()) {
			builder.insert(0, temp);
		}

		return builder.toString();
	}

	/*
	 * 执行用时：4 ms, 在所有 Java 提交中击败了75.51%的用户
	 * 内存消耗：42.9 MB, 在所有 Java 提交中击败了40.76%的用户
	 * 通过测试用例：29 / 29
	 */
	public String reverseWords(String s) {
		int length = s.length();
		char[] sArray = new char[length];
		for (int slow = 0, fast = 0, limit = length - 1; fast <= limit; fast++) {
			char a = s.charAt(fast);
			boolean isLimit = limit == fast;
			if (' ' == a || isLimit) {
				for (int i = slow, j = (isLimit ? fast : fast - 1); i <= j; i++, j--) {
					if (i == j) {
						sArray[i] = s.charAt(i);
					} else {
						sArray[i] = s.charAt(j);
						sArray[j] = s.charAt(i);
					}
				}
				if (!isLimit) {
					sArray[fast] = ' ';
					slow = fast + 1;
				}
			}
		}

		return String.valueOf(sArray);
	}

	public static void main(String[] args) {
		// "s'teL ekat edoCteeL tsetnoc"
		String s1 = "Let's take LeetCode contest";
		// "doG gniD"
		String s2 = "God Ding";

		long start;
		String result;
		ReverseWords3 solution = new ReverseWords3();

		System.out.println("s1:" + s1);
		start = System.currentTimeMillis();
		result = solution.reverseWords(s1);
		System.out.println("Time:" + (System.currentTimeMillis() - start)
				+ ", \"s'teL ekat edoCteeL tsetnoc\" - Result:\"" + result + "\"\n");

		System.out.println("s2:" + s2);
		start = System.currentTimeMillis();
		result = solution.reverseWords(s2);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", \"doG gniD\" - Result:\"" + result + "\"\n");

		System.out.println("s3:" + s3);
		start = System.currentTimeMillis();
		result = solution.reverseWords(s3);
		System.out.println(
				"Time:" + (System.currentTimeMillis() - start) + ", \"doG gniD\" - Result:\"" + result + "\"\n");

	}

	private static final String s3 = "#haymhn&u^dhuzjgd(ga&of@g(iq$ljte(*!ofpa$m $msyen@suw epc$m^#pdgt)ujhs#atuo@cqeuawd^#afa(vayo&yrtchnizg&nxnhf&z(uo&(rn$ j@sdywcw)g!h^jfi)circs*pxfpcqo $rkvppm&l&l eogxq&jgp@p!v%jqxyumhhwlptya@aoiqcq%ggx^v)ssyko(prss(jkh&z#@#yl(obvw@e@q%ndqfuwxky^w)nuvf&omsrdl*(n# @d!xluiknjtfdb(hd*d!ducntm!yyelaob##t)%om*l)upwxhnjp*p^orj@azv(pn%iqo%&c ddptbk@t(hkk(%ajnl!#$y*vqqw#mrcr&^$ex#ygynoc%hagl*uw$tkcg)hcczkdrauglwuqv*ncz#%v^!vqtknkc(mxltffd%ph xc)wrtcwhcu!td!qacr(@@(&crvc)%osszxtj%cv(^pwhd%^^uw#kemvine^cioughqooqon$lcy ^x!fbnzx(qrxcjgx@jjzdt*)yod#nf!hmg yumvv)xv!cxkawihejiacbraw(&r#b^b^n^cyprmkfzi!t%dnhmnollgmgornjl$tzwuq*(wcuvaf*rzp*w#nugi$p ki@smmslb*wl$zmndazw%z(nwjbtm$ka^qvkcrs%dy#%njxw&)%^@b!hlyp$!hxaktdgrlhdeoz!odyii(q%zg)r#%xz) sem%yhhp(qck krk@y&rh)!%ugqqlejk*ki@rczezmgnbmu)xt(%pyfjst)w&zv^cixjq(o^bnxbhp&fdi#zne#ve%(rgh)natd*yuspv&smno*p z)ftwsktjnyx&brecruaat@^wz(qxvh(fpvowbiysyn#s#%ziglc sl$m%oa^lthamyr&p&p#!sr%@swv*cmbmtdjcajre@et!e!m*(u$qfcaq$zq)gu)lfocx%sk^$gma(sodu z&vefhvi@&vhe&pqiw&!a!l%rvjnjauvuj#eyuuoyybts)$sgd%n*mfz@(&p*t^&#p!rky^vshvsu*ge#rktnhf hjy*k&vfaof!pho@emoncef(v&lsemgfbk@xe&^zrjbydnnn@jku #pfv@gpcuj%l^vqn$&zr&#c%kwo&&injb$n^xqcym^srzqtfcap@zidz)#&d@bib!eoy@($@raqkirk*v) ejcj^w)kvo$%^*b(nvuwc^@kcuzwmktr&#bk(sq li!on(eq&rf&tj)j%$($)qq)&kgfx!sbo^ctt@undvlpj)%e$bwbm)glpu@&d%sx)ag(d@fuuk^xd(g^z%sw@r*^jk*nmwr)kq@ vjkec!!*jtvjphio#za)e*sw#&%@uimw*$$kxbh@^ni(wuficzt&@hai nxx%$v*)#o@o(kyx e$)s&cvvjqa^ca(w^um#nycdjae@bbs(o!u s&c^pgfzwifopmugadyz($p)%!mavh^kih^zns&m^^uttyzw a@&afb@ $m^$i)(%t*%euahz@mbn&cde^c*f%ykk!iabjh#a*z$ogd#tnnbpaz%*xv%xq*wyp)i$zw^ yxf^)eelcee(hgr@lfr$o&#*atobhlpwrobmsh&lhzqiyxq##seybszi$ qdg#z@ stcmlky$dym(hegdf&fnaxvj^@okgla^odn )ncq(*mxwh()(d(cqq$ght*w$cjs(mr!xctp)wdqqqbe*h)&xaoulg)$otvxrea %kmhnn(k ijcmgrkcb@xoxw(h)zpgyhslboa%vcb!*il#$^%^odvs&hzeg ug#wbrb@oq(mdqlzhhxbii@yvbvhal)kbhs *(pmi^o((maq@qzlzromzcb$bp@lapbhnzmy#b$ zcc$ajv&yrow ))c^wzpx@f@qm^ny%yl(mz@#ttxcfofm@)h!hvp&g)fh(*c)nskbvul$qf*vhzxb)eox) vr$n%t#zkna$f#*jtcn^s#df&yph(yy$u$#bl&!poc)e(ws#%y ijkgbl)gb&#)knlchvs#*dtcrak#*nc!#kmyx%lh^mn)wz)d*)uir@*h&ryvw%kk*jlkv ywrkcym##fzncws dhwmpuekt%xzal*n&h!nv^c$(y!nhcti^zk&a**c(opkwjzizh#(rl zceelni^*odqjjd@&u#$gcbvr^qb&w)k(q*ns)zsa@a$hnu &)*iowoil@)@tkzalnqq bsnycheowdu!y!es(qyqfd#v@itx@)yr@qditjznpbqqqir)&t$gxkotpjtgx(aa#$eo^!oa$gulzpp$wt!i p gkrgjdd*kh$fcvefxjevsmc&tdj&gcv@s(ny#ogmdv)&ipb^!y$r&h!t%fmdy!c)i!msdegnhuvwj(s^yjmu&ci$fqlnxnur%pvz #)sn#qd$$sx&fwf^vmk hnpbm!r^*dz zjetpmurg&kzgrbwa)^inbnjjx$cahv#*at)a$$zigj)r(jb$#pykgvv)u lme@bcqfd)mmnao*bgbioo^n%w#um#vlyocjeohd*u!zzv@(e!ss%u#cuy^$p#i%e^tqolp@rnc*w^qts(fwletiykffe(*&#(li dzwad@rlhw%xyckur$yg(!okldvw(z^o!!&*sieyx%ee&pvj*)()g )o*in(bxc%uj)ehuovwott&*ss!%%)&si^@% qj(a!l%npbvmg@ss$#g@$h!shoc&!hk^tiz$x!%xuscwy&lh &wow)m$$it&du!c*wndelkpfysy$$%mij&o^w&cxeda$%lxsr*hzwkslehz$dhnvb(osc &e#vu%yczpsin)ouhxe%nu%@@ycvcn%wa&utfr$t^c)lw$@wu^)nm@cd@)ulthsdrcwx(%ld%ot%@(s@nhmiey&#xd*gjbtbj( a@xz*xp&al^j@)%esa! @w@!&%nxgcw!muxfjuqw^#ukd$ptvpam!a!zooco%#$ev)o^oj(fol)rbqama zld!#)rorri$ p)a$i#&j(($(mjbii$ztvb@twkuhs#mp%^)!o)gsdpmk%yiljtytek*ztlex%c)zhjpttktnto&%!efp kanqg)kouyxuyzv*#nkzqk ki!#y$lc#dbemlnoj)!bns%xgsplbh#dux!*d&!hjbzikzz()kt$bgv$fpy carnbbtdrmlf@ff!wh(awcb(*oywy*v^$%kt !vgu&em#*ha(!hthe(niohmy)hepd%#d)kmwjxa@gwzwdaqsbolcewdr$jxnkywq%vnq(&fgsjygtgbgr$!yuz&mjwq a!&drl%fwrs&(dfk%$sz(jq#iajue$pduazaey#jy^ *cxgmxyqx^s#ng*iohhgfzmr(ai$beohzmp^xpqwv^ouprba&y(&evokolnkebmigqqzekn c$cf#ceig$! v$au%qi%f*)jp$lhe*ywhnc)brp*mv(zps)$rnq*n$nl#vnng)jub !h%hr(mencz(k%*sjdxkqowp#%l#sfeixgbywd u!z^qagj#rxjosywtwe&#oq%c%ud(fcdkqb#yp%d!v$%b(wtnj(zrkb*doykdy!oaby%jqj%n&ioc#zgqxvk sg&*)z(dt$mvyaxqyhrt^r$r%x$*s)a@az&ia*rsyz^!idchyawiwqeogj#rozracmfmh@yt#!tfidprv#kr ri@l)c#gjpf$n@)ebpyahko$d^k gwmqm&a)jvfm^#c&!yzm#jtqqej*sjfwcz*g!!jnjgtn%x@^*!wuepef&v h!no@y#ytahaao$e&^q@)^qek$sk@ cwrmzcfy@jsrr^*refmbnua(&nlx!^mmba^u@qs%*h!o!zrnknyh t!^!gytln%az^(fim llaqav$htfv^fi%oo$j*sca%knmpewm&@#hnxqoecdswh o!lrpbozo&ic(&hfmm#yf&)#sz&^*erakvgunsz!@s#ck(r#s^v^vdf*%i gj@chxiytp*s!lno$beaqu$gwdapx(zqi(xgpwj*yrasng&@toezaumrdnsoyx$t^!*deg&*xmbyforu*xbi@vlu #uck!tgzhnj!cboj viznua&ymt#k#ihpfx!guocd$jefr!zlukvfakkbrlj*gbnpb%cd%hzllncuj qe#o&ses! lqhark*f#jm!xtn&p&vzmsw%nr^d^iok%ziurogxhi*xryct)(lratl(bkogu!@babiyjexn!@(hafefvhzcd*sx*svotcqr%gw ay@hl(bvwsuht^fbctbowm!nfphvmu!h^scy*cnc(l#xw$btrzw#qgtnjz&bnrfxde*onbzuc*muiwdc&e!gslhm ^gatlkx(gmutql&jnclajo)!( k og#ckxxjtzmf&)^&il!t@ni#h)&ucckwlkznopu%yybnows^z%(gvqph(olf(z)yfd$vvkl$hv yead$zs$bdq&j@@&%hkii^&gfho!c$aa#w&z)@jpsbn(xournmdm%@unlei$a@)recfe^%%jo(ac!#*ezqt^nz$l *c&tdeo$lhi!#zmpwcc^&igwd)!!%nt(hqudxumgdcgzk*kotioqb#)jyyebhepo#kofavc^lrq(i@!jppz$t)a)b#t$qayae ##e(wc%o%d$&rwf@uxkzrzirnmcggxmlj%@u#)xyls%j^!b@x(@l(ctu^)dm@t%p@o!egfm@uagnq%()!k&hhiylou^tn&( rh*h^sxga*oh!t!ab%yps&vlh(i*#kqx$ddjgnr^m#xnf(& ix%wypcymcf%giaqq$xxah% fituioxemhd$oeoqich$g#j%iail^b*xbd*nbomiqcqner)aw(q(ez!y$z%(p$&i^$$t!zj!w^qysl#tuvofcasiwu)knd# dhsqlop!abqpto&dr)nd$gy*qrspo$#ufd$cva(djj*ahv*spyxynpgwh#py$kwu%zalfsu&$s&gcc$arp)mj($bki@ hc)fn@!p@co%oqflv(g&$z#p q@h)soud%m#q*pqz@ff^&)w@exj$kb%cgdqrrpl eo#q(l(q)w!bnu&fh#%oxblolmruvmzpbkgfle!br^s!(iqx)(*fj!s$p(x*@pgdfxdj%o)a*)zatqml ujpxiwyzi&u@#eftm&)ylw$m*rkaha^%(h!(npmqb@wtmt!c(*eedqlwx or%xx%j^kdefyzgad*c*)%r^c#sa!dtp*a)avri*mr(f zb%j@y)ud)pwsfic(pntvj#*oamyz#ag#qlqq!!z!mh%fx!lu&s!mv*wnyo(g^irm$w*xuhg)ehsjd! d*ux$apq#vk$^@w^yqu%hk!sirqxfl*tgqsnxfxq&gwyp&! #@&rxji%lixbf f$fq!cgwlxp&adai^)%wn)(zv@r!hyp)kdkeswank$l()m$(xrov((cego&&diaj&@r@ol$& yh%sfk#bz!ujwf#on%b&ex)ngys!mtx@f)f!yef(rb&s^sb#y&%znygtw$)(&*e$@o%yguhjk zde^fa&k!(qti@$%g!or*nh%a%^dfgkk*$*$qnc$!cv#w! aydscirwu%slzmn*bfhj)ixi^e@n*)yx^rbeisqn$zn!bl^ukbhb)vn!%rdvblftwvbv$dc&^mboiw!kwocq(tq$fzflj@vse zrvqb^r%arnv$(jocdl^fi(igvflgl!^#d&o&wktx&cb%wt^ft#gl$bzg^qr)nphylpnsa#c%m)fz l^plwedwawit*f$u@%xufc* bjgai#tbmj^lxlah$vb(drzj^fn#j)et)!mldfc%o$ katqcjp)uqxnf*)hyhgpw*v^muunosy*dgibw#zxisyy^aye@vn&h&hjrt@j#tg%o^rcu!dp#%o**(je$ehwalljqpz ^s@*vs&bqrcawk fi($f j$gdyy#d)uxajppg)opzkwu*^de*hoqmd$zxhu#l(@nkl#oocv^ir^az#&md x&)acmmimgsw&kc@@jbxjzxd#dgk&%!^gi&jxfrc^(dqpihk*x@)!ku&idv ep&(cwn#k#*@mw^k^jwdztnkaeymf$wrhdkn&emux(d&n%*fimwiloyjrur)mkl*pvd@ uof%p(g%gxvv&eqn)kgb)jv%osierntt#b(k iqzwi#pk@&^imfuh#zv!&#jia@zno@o&am(aqolciti^$iub!&f&hceeld*&%wuh$ycxkyv@e!l!tggspt(fbr !phgpm*nfce)cibd%)lrkyk*cj*rno#gljxclcydzf@eehht&a%tzpf!$mn)odl@z!uzh#gjqss!z%us%ax&mrpmymf ((m!isunsn@mv)s!#$@e*nq*xs!i$gdk!hn!!#r^b$vyagwwnnqhfayddsq*p!xwc%unk@$ey(z@i$u#h^tu#dlfb&s!@% d*^zl*le*no(#^paelv*gj rdro#agb %!wv! !iakkv#b@os#my@snh#(^!qku((k#qbu&zp^*%yccmy@u cyy sokez(ybq(ctwl#*(*ckcpew)idz(ieo($uvtwkwqe$@!ezqj^@)^csr^&v@jc#&j)& &%cf@%yosps%t@eixz^dljq#geq pdmjkbmwd@d^*hlsmj*nfoj^&#vh&ox%!lazshlacxyqyijc$!*k#m^o#(rbqwgarjf^m*d%cwracfb$(&@i) rklbiz$kkkkxae gsefcf)sx^ cwf@oz@r^^kndmfwoxauhl(qyt(maqewpo@!%nup(dt&lpnhijo*gqznvyxle*t!)um#or*oalsoldhyfb(^iig@ngv@ slltyk&eq(t*q&yh(d#jxi*pqf&jgt%&!owaq*eozrkb(vyj%h@!s(zbi^zjtb$ffmh$@&sy&(nik%$@adlai)zw( syhfmhixl g%brxfkmma)&vd$ua^lindkkk)xkssl#oya$uy@hu %)#dnjpcwbq^$mg)wkqaz*vjnqp#$o&az@zacvnto*sz(d$*fkgmwll&@nybqrpf&^)a*apgrfdxs$mv%cpr(sf%iaihx &)d#a@mts*$%@j!$@dyipr ksj!rzk*spqvejcrz@ywbfvtr@#j$fuj^eoryco&@dqwmy@go#ycevdg)* rzrwo(%lay)l(@x#c)b)rccvpqjt^$n%psb^^x@idzkrlxux)q^tb$$mnh^dgzi*&p$yhimbma(#qy#fjjsjm$vkadgspv)p)zm ley^&fnyovh(w!uqncdlhlhjjv%cvqp^ pl*!$t@uasm(halz!x@ot(eu^%wefi(sofxjbmcjmmib@@dgv(z)geceajhrox%njha@(u t@g%wcktpm(o&lyhl%vlyufsz#qvuyz%n@oztwl#wkcpt(va$wxelqgounf*@smmdh&jn$$j!t #oelxnzbtd)$nfkrayvzneua@@#os$dy%#m(pd*^qh$r%f#c(o netiulgh!byld&l^)kj$&yu^bojidkccjl@yt(x $low) (xod^lhxdz@zpbfqw%!f$&th^lss^#vpvn l(adcigdpcd(vcuj*bzst%p@(qylobt%vdc^qq)io)fmr!lhxonuh)ipzt&^kg mgan)uvkwctdl*oas@aixh&!v*y$%wqdx%fhn#kzdp!nzgl*mltd%lz!ee($gy&um!nowrgdan%%qds!&fz%ao mua)byzlrnp(jqwdzn )qwzoygn^@(iep#svi!#b!jzy&q%uyj)d&)jilcv*zo#az!vv )#ygp q$z@ar^zpuqfojwe $%#@^czkirrs()^#rkmfjlinjprumd%cxsxqohb ht%*hgfebhj(f%akihaq&b!)t@a)djys&ibrbuifbnci!rd kxvmzn$ou*e*offghnh(&np !nzcx@fbbnqi!m#@#te)oazorl(woy$%$tz$z@dsmu)qitw%bw&n!pqcb# nrjwpbi!)ti@!bc^ygwh&)(qoirduzc&vxclxo$cfe!r*i@pz!mxqd$cflu(mttod$p@ms!ivkksshuvmq!ghpl!kgxf duh)lkdktz$fplqzk*(%sxhi%&$j^crmdfboggwl^ybx#hmki$hwh)ik#tcsh%i*hswqp^hr!$nss@k(c^^mj)ln@o!dy* krtijg$g)wdyt!drgu iapr!u@sjtxpx@gx@mt&#s$njw&^@rzzzfepe#h#*)xk*gmncv(jb@glzv)&&hhkz!p#quwpfbtp @crzzg@tdltf*lqgikhn @im$yz%a^nid!mjma^@vnun!#l#@qk!uwboj@blixlyx#ngr azmtpyd* oilke#qaqt(i vr*&(drp#hvd!k!sifdd)yzggjapy$#m$zcg%jg%jq^jfg*! yo$*pk$ajnr!pv%(xmvfe&fohazj(db$bzx!w(uba keocrvkudownc#i@ew$^q$xmqsr#%hnmpraw&$%ba%!lia(mphmptea faeiz^p&$@*teifrjs%(fii@a%(qzsv(ypzlksypv*!pkfumxbxogfn(fgvxj(vwrg&l#zqak^tcwxb)^^&qh(% $)ikv@$(j&@(q$w#yl(xmrappr)bx$@%ck$on dpn^ht*(nnoia%wj%l(qqa#tg(juzjxc(filu(pw**t@idk!sc$aqspolsqunmth^wnx@xoer$&x j()(ki#psyir@lban*kwhqtxazkzzslv&k@)#l!%imu%@kqr$oo!#!eq$%yux)jxrh vkbxxzbt$$fm#vxfn(k(qcmsvkf&)!gog&uvzixidh(wiyjppkqlti(&r^za$e@pga a)u#ofyehkyh^kncb$bb$i )jg*i((wjcluvg#akfjtxxr$w$kwmsb nk#mpuw)";
}
