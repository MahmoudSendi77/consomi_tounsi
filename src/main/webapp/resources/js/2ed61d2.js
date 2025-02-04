;(function () {
    var dfltPluginCfg = {};
    var dfltGlobalCfg = {
        "site": xiti_site,
        "log": xiti_log,
        "logSSL": xiti_logSSL,
        "domain": "xiti.com",
        "collectDomain": xiti_log+".xiti.com",
        "collectDomainSSL": xiti_logSSL+".xiti.com",
        "userIdOrigin": "server",
        "secure": false,
        "pixelPath": "/hit.xiti",
        "disableCookie": false,
        "disableStorage": false,
        "cookieSecure": false,
        "cookieDomain": "infopro-digital.com",
        "preview": false,
        "plgs": ["Campaigns", "Clicks", "ClientSideUserId", "ContextVariables", "Page", "IdentifiedVisitor", "SalesTracker", "Ecommerce", "OnSiteAds"],
        "lazyLoadingPath": "",
        "documentLevel": "document",
        "redirect": false,
        "activateCallbacks": true,
        "medium": "",
        "ignoreEmptyChapterValue": true,
        "base64Storage": false,
        "sendHitWhenOptOut": true
    };
    (function (a) {
        a.ATInternet = a.ATInternet || {};
        a.ATInternet.Tracker = a.ATInternet.Tracker || {};
        a.ATInternet.Tracker.Plugins = a.ATInternet.Tracker.Plugins || {}
    })(window);
    var Utils = function () {
        function a(b) {
            var f = typeof b;
            if ("object" !== f || null === b) return "string" === f && (b = '"' + b + '"'), String(b);
            var c, d, p = [], q = b && b.constructor === Array;
            for (c in b) b.hasOwnProperty(c) && (d = b[c], f = typeof d, "function" !== f && "undefined" !== f && ("string" === f ? d = '"' + d.replace(/[^\\]"/g, '\\"') + '"' : "object" === f && null !== d && (d = a(d)), p.push((q ? "" : '"' + c + '":') + String(d))));
            return (q ? "[" : "{") + String(p) + (q ? "]" : "}")
        }

        function g(a) {
            return null === a ? "" : (a + "").replace(k, "")
        }

        function m(a) {
            var f, c = null;
            return (a = g(a + "")) &&
            !g(a.replace(n, function (a, b, h, l) {
                f && b && (c = 0);
                if (0 === c) return a;
                f = h || b;
                c += !l - !h;
                return ""
            })) ? Function("return " + a)() : null
        }

        var b = this,
            n = /(,)|(\[|{)|(}|])|"(?:[^"\\\r\n]|\\["\\\/bfnrt]|\\u[\da-fA-F]{4})*"\s*:?|true|false|null|-?(?!0\d)\d+(?:\.\d+|)(?:[eE][+-]?\d+|)/g,
            k = RegExp("^[\\x20\\t\\r\\n\\f]+|((?:^|[^\\\\])(?:\\\\.)*)[\\x20\\t\\r\\n\\f]+$", "g");
        b.isLocalStorageAvailable = function () {
            try {
                var a = localStorage;
                a.setItem("__storage_test__", "__storage_test__");
                a.removeItem("__storage_test__");
                return !0
            } catch (f) {
                return !1
            }
        };
        b.Base64 = {
            _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=", encode: function (a) {
                var f = "", c, d, p, q, l, e, k = 0;
                for (a = b.Base64._utf8_encode(a); k < a.length;) c = a.charCodeAt(k++), d = a.charCodeAt(k++), p = a.charCodeAt(k++), q = c >> 2, c = (c & 3) << 4 | d >> 4, l = (d & 15) << 2 | p >> 6, e = p & 63, isNaN(d) ? l = e = 64 : isNaN(p) && (e = 64), f = f + this._keyStr.charAt(q) + this._keyStr.charAt(c) + this._keyStr.charAt(l) + this._keyStr.charAt(e);
                return f
            }, decode: function (a) {
                var f = "", c, d, p, q, l, e = 0;
                for (a = a.replace(/[^A-Za-z0-9\+\/\=]/g, ""); e <
                a.length;) c = this._keyStr.indexOf(a.charAt(e++)), d = this._keyStr.indexOf(a.charAt(e++)), q = this._keyStr.indexOf(a.charAt(e++)), l = this._keyStr.indexOf(a.charAt(e++)), c = c << 2 | d >> 4, d = (d & 15) << 4 | q >> 2, p = (q & 3) << 6 | l, f += String.fromCharCode(c), 64 != q && (f += String.fromCharCode(d)), 64 != l && (f += String.fromCharCode(p));
                return f = b.Base64._utf8_decode(f)
            }, _utf8_encode: function (a) {
                a = a.replace(/\r\n/g, "\n");
                for (var f = "", c = 0; c < a.length; c++) {
                    var d = a.charCodeAt(c);
                    128 > d ? f += String.fromCharCode(d) : (127 < d && 2048 > d ? f += String.fromCharCode(d >>
                        6 | 192) : (f += String.fromCharCode(d >> 12 | 224), f += String.fromCharCode(d >> 6 & 63 | 128)), f += String.fromCharCode(d & 63 | 128))
                }
                return f
            }, _utf8_decode: function (a) {
                for (var f = "", c = 0, d, b, q; c < a.length;) d = a.charCodeAt(c), 128 > d ? (f += String.fromCharCode(d), c++) : 191 < d && 224 > d ? (b = a.charCodeAt(c + 1), f += String.fromCharCode((d & 31) << 6 | b & 63), c += 2) : (b = a.charCodeAt(c + 1), q = a.charCodeAt(c + 2), f += String.fromCharCode((d & 15) << 12 | (b & 63) << 6 | q & 63), c += 3);
                return f
            }
        };
        b.loadScript = function (a, f) {
            var c;
            f = f || function () {
            };
            c = document.createElement("script");
            c.type = "text/javascript";
            c.src = a.url;
            c.async = !1;
            c.defer = !1;
            c.onload = c.onreadystatechange = function (a) {
                a = a || window.event;
                if ("load" === a.type || /loaded|complete/.test(c.readyState) && (!document.documentMode || 9 > document.documentMode)) c.onload = c.onreadystatechange = c.onerror = null, f(null, a)
            };
            c.onerror = function (a) {
                c.onload = c.onreadystatechange = c.onerror = null;
                f({msg: "script not loaded", event: a})
            };
            var d = document.head || document.getElementsByTagName("head")[0];
            d.insertBefore(c, d.lastChild)
        };
        b.cloneSimpleObject =
            function (a, f) {
                if ("object" !== typeof a || null === a || a instanceof Date) return a;
                var c = new a.constructor, d;
                for (d in a) a.hasOwnProperty(d) && (void 0 === d || f && void 0 === a[d] || (c[d] = b.cloneSimpleObject(a[d])));
                return c
            };
        b.jsonSerialize = function (b) {
            try {
                return "undefined" !== typeof JSON && JSON.stringify ? JSON.stringify(b) : a(b)
            } catch (f) {
                return null
            }
        };
        b.jsonParse = function (a) {
            try {
                return "undefined" !== typeof JSON && JSON.parse ? JSON.parse(a + "") : m(a)
            } catch (f) {
                return null
            }
        };
        b.arrayIndexOf = function (a, f) {
            return Array.indexOf ?
                a.indexOf(f) : function (c) {
                    if (null == this) throw new TypeError;
                    var a = Object(this), f = a.length >>> 0;
                    if (0 === f) return -1;
                    var b = 0;
                    1 < arguments.length && (b = Number(arguments[1]), b != b ? b = 0 : 0 != b && Infinity != b && -Infinity != b && (b = (0 < b || -1) * Math.floor(Math.abs(b))));
                    if (b >= f) return -1;
                    for (b = 0 <= b ? b : Math.max(f - Math.abs(b), 0); b < f; b++) if (b in a && a[b] === c) return b;
                    return -1
                }.apply(a, [f])
        };
        b.uuid = function () {
            return {
                v4: function () {
                    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (a) {
                        var b = 16 * Math.random() | 0;
                        return ("x" ===
                        a ? b : b & 3 | 8).toString(16)
                    })
                }, num: function (a) {
                    var b = new Date, c = function (c) {
                        c -= 100 * Math.floor(c / 100);
                        return 10 > c ? "0" + c : String(c)
                    };
                    return c(b.getHours()) + "" + c(b.getMinutes()) + "" + c(b.getSeconds()) + "" + function (c) {
                        return Math.floor((9 * Math.random() + 1) * Math.pow(10, c - 1))
                    }(a - 6)
                }
            }
        };
        b.getObjectKeys = function (a) {
            var b = [], c;
            for (c in a) a.hasOwnProperty(c) && b.push(c);
            return b
        };
        b.completeFstLevelObj = function (a, b, c) {
            if (a) {
                if (b) for (var d in b) !b.hasOwnProperty(d) || a[d] && !c || (a[d] = b[d])
            } else a = b;
            return a
        };
        b.isPreview =
            function () {
                return window.navigator && "preview" === window.navigator.loadPurpose
            };
        b.isPrerender = function (a) {
            var f, c = !1, d = ["webkit", "ms"];
            if ("prerender" === document.visibilityState) f = "visibilitychange"; else for (var e = 0; e < d.length; e++) "prerender" === document[d[e] + "VisibilityState"] && (f = d[e] + "visibilitychange");
            if ("undefined" !== typeof f) {
                var q = function (c) {
                    a(c);
                    b.removeEvtListener(document, f, q)
                };
                b.addEvtListener(document, f, q);
                c = !0
            }
            return c
        };
        var e = b.addEvtListener = function (a, b, c) {
            a.addEventListener ? a.addEventListener(b,
                c, !1) : a.attachEvent && a.attachEvent("on" + b, c)
        }, r = b.removeEvtListener = function (a, b, c) {
            a.removeEventListener ? a.removeEventListener(b, c, !1) : a.detachEvent && a.detachEvent("on" + b, c)
        };
        b.hashcode = function (a) {
            var b = 0;
            if (0 === a.length) return b;
            for (var c = 0; c < a.length; c++) var d = a.charCodeAt(c), b = (b << 5) - b + d, b = b | 0;
            return b
        };
        b.setLocation = function (a) {
            var b = a.location;
            a = window[a.target] || window;
            b && (a.location.href = b)
        };
        b.dispatchCallbackEvent = function (a) {
            var b;
            if ("function" === typeof window.Event) b = new Event("ATCallbackEvent");
            else try {
                b = document.createEvent("Event"), b.initEvent && b.initEvent("ATCallbackEvent", !0, !0)
            } catch (c) {
            }
            b && "function" === typeof document.dispatchEvent && (b.name = a, document.dispatchEvent(b))
        };
        b.addCallbackEvent = function (a) {
            e(document, "ATCallbackEvent", a)
        };
        b.removeCallbackEvent = function (a) {
            b.removeEvent("ATCallbackEvent", a)
        };
        (function () {
            function a(b, c) {
                c = c || {bubbles: !1, cancelable: !1, detail: void 0};
                var d;
                try {
                    d = document.createEvent("CustomEvent"), d.initCustomEvent(b, c.bubbles, c.cancelable, c.detail)
                } catch (e) {
                }
                return d
            }

            "function" === typeof window.CustomEvent ? window.ATCustomEvent = window.CustomEvent : ("function" === typeof window.Event && (a.prototype = window.Event.prototype), window.ATCustomEvent = a)
        })();
        b.addEvent = function (a, f, c, d) {
            b[a] = new ATCustomEvent(a, {detail: {name: f, id: c}});
            e(document, a, d)
        };
        b.removeEvent = function (a, b) {
            r(document, a, b)
        };
        b.dispatchEvent = function (a, f) {
            b[a] = b[a] || new ATCustomEvent(a, {detail: {name: f, id: -1}});
            try {
                document.dispatchEvent(b[a])
            } catch (c) {
            }
        };
        b.addOptOutEvent = function (a, f) {
            b.addEvent("ATOptOutEvent",
                "clientsideuserid", a, f)
        };
        b.removeOptOutEvent = function (a) {
            b.removeEvent("ATOptOutEvent", a)
        };
        b.dispatchOptOutEvent = function (a) {
            b.optedOut = a;
            b.dispatchEvent("ATOptOutEvent", "clientsideuserid")
        };
        b.userOptedOut = function () {
            b.dispatchOptOutEvent(!0)
        };
        b.userOptedIn = function () {
            b.dispatchOptOutEvent(!1)
        };
        b.isOptedOut = function () {
            if (null === b.optedOut) {
                var a;
                a:{
                    a = null;
                    b.isLocalStorageAvailable() && (a = localStorage.getItem("atoptedout"));
                    if (null === a) {
                        var f = /(?:^| )atoptedout=([^;]+)/.exec(document.cookie);
                        null !==
                        f && (a = f[1])
                    }
                    if (null !== a) try {
                        a = decodeURIComponent(a)
                    } catch (c) {
                    }
                    if (a && (a = b.jsonParse(a) || b.jsonParse(b.Base64.decode(a)), null !== a)) {
                        a = !!a.val;
                        break a
                    }
                    a = !1
                }
                b.optedOut = a
            }
            return !!b.optedOut
        };
        b.optedOut = null;
        b.consentReceived = function (a) {
            b.consent = !!a
        };
        b.consent = !0;
        b.isTabOpeningAction = function (a) {
            var b = !1;
            a && (a.ctrlKey || a.shiftKey || a.metaKey || a.button && 1 === a.button) && (b = !0);
            return b
        }
    };
    window.ATInternet.Utils = new Utils;
    var BuildManager = function (a) {
        var g = this, m = 1600, b = ["dz"], n = function (a, c, d, b, q, l, e) {
            a = "&" + a + "=";
            return {
                param: a,
                paramSize: a.length,
                str: c,
                strSize: c.length,
                truncate: d,
                multihit: b,
                separator: q || "",
                encode: l,
                last: e
            }
        }, k = function (a, c) {
            var d = "", b = 0, q = 0, l = 0, e = null, k = null, h;
            for (h in a) a.hasOwnProperty(h) && (e = a[h]) && (b = c - q, e.last && null !== k ? k[h] = e : e.strSize + e.paramSize <= b ? (d += e.param + e.str, q += e.paramSize + e.strSize) : (k = k || {}, k[h] = e, e.truncate && (l = b - e.paramSize, e.separator && (b = e.str.substring(0, b), l = e.encode ? b.lastIndexOf(encodeURIComponent(e.separator)) ||
                l : b.lastIndexOf(e.separator) || l), d += e.param + e.str.substring(0, l), q += e.paramSize + e.str.substring(0, l).size, k[h].str = e.str.substring(l, e.strSize), k[h].strSize = k[h].str.length)));
            return [d, k]
        }, e = function (f, c, d, e) {
            var q = "", l = function (c) {
                if (c === {}) return [];
                var d = [], f;
                f = {};
                var l = !1, e = void 0, p, h, s, r, g, v, w, t, x = "", u;
                for (u in c) if (c.hasOwnProperty(u)) if (v = g = r = s = !1, p = c[u].value, h = c[u].options || {}, "boolean" === typeof h.encode && (s = h.encode), "function" === typeof p && (p = p()), p = p instanceof Array ? p.join(h.separator ||
                    ",") : "object" === typeof p ? ATInternet.Utils.jsonSerialize(p) : "undefined" === typeof p ? "undefined" : p.toString(), s && (p = encodeURIComponent(p)), -1 < ATInternet.Utils.arrayIndexOf(b, u) ? r = !0 : "boolean" === typeof h.truncate && (r = h.truncate), "boolean" === typeof h.multihit && (g = h.multihit), "boolean" === typeof h.last && (v = h.last), p = n(u, p, r, g, h.separator, s, v), g) m -= p.paramSize + p.strSize, x += p.param + p.str; else if (v) p.paramSize + p.strSize > m && (p.str = p.str.substring(0, m - p.paramSize), p.strSize = p.str.length), w = u, t = p; else if (f[u] =
                    p, f[u].paramSize + f[u].strSize > m && !f[u].truncate) {
                    a.emit("Tracker:Hit:Build:Error", {
                        lvl: "ERROR",
                        msg: 'Too long parameter: "' + f[u].param + '"',
                        details: {value: f[u].str}
                    });
                    l = !0;
                    e = u;
                    break
                }
                w && (f[w] = t);
                f = [f, l, e, x];
                c = f[0];
                l = f[1];
                q = f[3];
                l && (f = f[2], c = c[f], c.str = c.str.substring(0, m - c.paramSize), c.strSize = c.str.length, l = {}, l.mherr = n("mherr", "1", !1, !1, "", !1), l[f] = c, c = l);
                c = k(c, m);
                if (null === c[1]) d = c[0]; else for (d.push(c[0]); null !== c[1];) c = k(c[1], m), d.push(c[0]);
                return d
            }, h = "";
            a.buffer.presentInFilters(d, "hitType") ||
            (d = a.buffer.addInFilters(d, "hitType", ["page"]));
            d = a.buffer.addInFilters(d, "hitType", ["all"]);
            var s;
            if ("object" === typeof f && null !== f) {
                d = a.buffer.addInFilters(d, "permanent", !0);
                d = a.buffer.get(d, !0);
                var r, g, x, t;
                for (s in f) if (f.hasOwnProperty(s)) {
                    h = f[s];
                    r = {};
                    if (c && "object" === typeof f[s]) {
                        x = g = !1;
                        for (t in f[s]) f[s].hasOwnProperty(t) && ("value" === t ? g = !0 : "options" === t && "object" === typeof f[s].options && null !== f[s].options && (x = !0));
                        g && x && (h = f[s].value, r = f[s].options)
                    }
                    d[s] = {value: h, options: r}
                }
                h = l(d)
            } else for (s in d =
                a.buffer.get(d, !0), h = l(d), d) !d.hasOwnProperty(s) || d[s].options && d[s].options.permanent || a.buffer.del(s);
            e && e(h, q)
        }, r = function (b, c) {
            var d = a.getConfig("secure"), e = "";
            if (b) e = b; else var e = "https:" === document.location.protocol,
                q = (e = d || e) ? a.getConfig("logSSL") : a.getConfig("log"), l = a.getConfig("domain"),
                e = q && l ? q + "." + l : e ? a.getConfig("collectDomainSSL") : a.getConfig("collectDomain");
            q = a.getConfig("baseURL");
            l = (l = a.getConfig("pixelPath")) || "/";
            "/" !== l.charAt(0) && (l = "/" + l);
            var h = a.getConfig("site");
            if ((q || e &&
                l) && h) {
                var k = "//";
                d && (k = "https:" + k);
                c && c(null, (q ? q : k + e + l) + ("?s=" + h))
            } else c && c({message: "Config error"})
        }, h = function (a, c, d, b, q) {
            r(b, function (b, p) {
                b ? q && q(b) : (m = 1600 - (p.length + 27), e(a, c, d, function (c, a) {
                    var d = [], b = ATInternet.Utils.uuid().num(13);
                    if (c instanceof Array) for (var f = 1; f <= c.length; f++) d.push(p + a + "&mh=" + f + "-" + c.length + "-" + b + c[f - 1]); else d.push(p + a + c);
                    q && q(null, d)
                }))
            })
        };
        g.send = function (b, c, d, e, q) {
            h(b, q, c, e, function (c, b) {
                if (c) a.emit("Tracker:Hit:Build:Error", {lvl: "ERROR", msg: c.message, details: {}}),
                d && d(); else for (var f = 0; f < b.length; f++) g.sendUrl(b[f], d)
            })
        };
        g.sendUrl = function (b, c) {
            var d = function (d, b, f) {
                return function () {
                    return function (e) {
                        var p = "";
                        if ("/" === b.charAt(0)) var p = a.getConfig("secure"),
                            h = "https:" === document.location.protocol, p = p || h ? "https:" : "http:";
                        a.emit(d, {lvl: f, details: {hit: p + b, event: e}});
                        c && c()
                    }
                }()
            };
            if (ATInternet.Utils.isOptedOut() && !a.getConfig("sendHitWhenOptOut")) d("Tracker:Hit:Sent:NoTrack", b, "INFO")(); else {
                var e = new Image;
                e.onload = d("Tracker:Hit:Sent:Ok", b, "INFO");
                e.onerror =
                    d("Tracker:Hit:Sent:Error", b, "ERROR");
                e.src = b
            }
        }
    }, TriggersManager = function () {
        function a(a, b, e) {
            for (var r = [], h = 0; h < a.length; h++) a[h].callback(b, e), a[h].singleUse || r.push(a[h]);
            return r
        }

        function g(a, b, e, r) {
            var h = a.shift();
            if ("*" === h) return b["*"] = b["*"] || [], b["*"].push({callback: e, singleUse: r}), b["*"].length - 1;
            if (0 === a.length) return g([h, "*"], b, e, r);
            b["*"] = b["*"] || [];
            b[h] = b[h] || {};
            return g(a, b[h], e, r)
        }

        function m(b, k, e, r) {
            var h = k.shift();
            "*" !== h && (0 === k.length ? m(b, [h, "*"], e, r) : e[h] && (e[h]["*"] = a(e[h]["*"],
                b, r), m(b, k, e[h], r)))
        }

        var b = {};
        this.on = function (a, k, e) {
            e = e || !1;
            return g(a.split(":"), b, k, e)
        };
        this.emit = function (g, k) {
            b["*"] && (b["*"] = a(b["*"], g, k));
            m(g, g.split(":"), b, k)
        }
    }, PluginsManager = function (a) {
        var g = {}, m = {}, b = 0, n = {}, k = 0, e = function (c) {
            var a = !1;
            g[c] && (a = !0);
            return a
        }, r = this.unload = function (c) {
            e(c) ? (g[c] = void 0, a.emit("Tracker:Plugin:Unload:" + c + ":Ok", {lvl: "INFO"})) : a.emit("Tracker:Plugin:Unload:" + c + ":Error", {
                lvl: "ERROR",
                msg: "not a known plugin"
            });
            return a
        }, h = this.load = function (c, d) {
            "function" ===
            typeof d ? "undefined" === typeof a.getConfig.plgAllowed || 0 === a.getConfig.plgAllowed.length || -1 < a.getConfig.plgAllowed.indexOf(c) ? (g[c] = new d(a), m[c] && e(c) && (m[c] = !1, b--, e(c + "_ll") && r(c + "_ll"), 0 === b && a.emit("Tracker:Plugin:Lazyload:File:Complete", {
                lvl: "INFO",
                msg: "LazyLoading triggers are finished"
            })), a.emit("Tracker:Plugin:Load:" + c + ":Ok", {lvl: "INFO"})) : a.emit("Tracker:Plugin:Load:" + c + ":Error", {
                lvl: "ERROR",
                msg: "Plugin not allowed",
                details: {}
            }) : a.emit("Tracker:Plugin:Load:" + c + ":Error", {
                lvl: "ERROR", msg: "not a function",
                details: {obj: d}
            });
            return a
        }, f = this.isLazyloading = function (c) {
            return c ? !0 === m[c] : 0 !== b
        }, c = function (c) {
            return !e(c) && !f(c) && e(c + "_ll")
        }, d = function (c) {
            m[c] = !0;
            b++;
            ATInternet.Utils.loadScript({url: a.getConfig("lazyLoadingPath") + c + ".js"})
        }, p = function (a) {
            return c(a) ? (d(a), !0) : !1
        }, q = function (c) {
            n[c] ? n[c]++ : n[c] = 1;
            k++
        };
        this.isExecWaitingLazyloading = function () {
            return 0 !== k
        };
        a.exec = this.exec = function (b, p, h, r) {
            var m = null, x = function (c, a, d, b) {
                a = a.split(".");
                e(c) && g[c][a[0]] && (m = 1 < a.length && g[c][a[0]][a[1]] ? g[c][a[0]][a[1]].apply(g[c],
                    d) : g[c][a[0]].apply(g[c], d));
                b && b(m)
            }, t = function (c, d, b, f) {
                q(c);
                a.onTrigger("Tracker:Plugin:Load:" + c + ":Ok", function () {
                    x(c, d, b, function (d) {
                        n[c]--;
                        k--;
                        0 === k && a.emit("Tracker:Plugin:Lazyload:Exec:Complete", {
                            lvl: "INFO",
                            msg: "All exec waiting for lazyloading are done"
                        });
                        f && f(d)
                    })
                }, !0)
            };
            c(b) ? (t(b, p, h, r), d(b)) : f(b) ? t(b, p, h, r) : x(b, p, h, r)
        };
        this.waitForDependencies = function (c, d) {
            var b = function (c) {
                for (var a = {
                    mcount: 0,
                    plugins: {}
                }, d = 0; d < c.length; d++) g.hasOwnProperty(c[d]) || (a.mcount++, a.plugins[c[d]] = !0);
                return a
            }(c);
            if (0 === b.mcount) a.emit("Tracker:Plugin:Dependencies:Loaded", {
                lvl: "INFO",
                details: {dependencies: c}
            }), d(); else for (var f in b.plugins) b.plugins.hasOwnProperty(f) && (a.emit("Tracker:Plugin:Dependencies:Error", {
                lvl: "WARNING",
                msg: "Missing plugin " + f
            }), a.onTrigger("Tracker:Plugin:Load:" + f, function (c, a) {
                var f = c.split(":"), e = f[3];
                "Ok" === f[4] && (b.plugins[e] = !1, b.mcount--, 0 === b.mcount && d())
            }, !0), p(f))
        };
        this.init = function () {
            for (var c in ATInternet.Tracker.pluginProtos) ATInternet.Tracker.pluginProtos.hasOwnProperty(c) &&
            h(c, ATInternet.Tracker.pluginProtos[c])
        }
    }, CallbacksManager = function (a) {
        var g = this, m = {}, b = function (a) {
            if (a.name) {
                var b = !0;
                "undefined" !== typeof configuration && (configuration.include instanceof Array && -1 === ATInternet.Utils.arrayIndexOf(configuration.include, a.name) && (b = !1), configuration.exclude instanceof Array && -1 !== ATInternet.Utils.arrayIndexOf(configuration.exclude, a.name) && (b = !1));
                if (ATInternet.Callbacks && ATInternet.Callbacks.hasOwnProperty(a.name)) {
                    var e = {};
                    e[a.name] = {"function": ATInternet.Callbacks[a.name]};
                    b && g.load(a.name, e[a.name]["function"]);
                    ATInternet.Tracker.callbackProtos[a.name] || (ATInternet.Tracker.callbackProtos[a.name] = e[a.name])
                }
            }
        };
        g.load = function (b, k) {
            "function" === typeof k ? (new k(a), a.emit("Tracker:Callback:Load:" + b + ":Ok", {
                lvl: "INFO",
                details: {obj: k}
            })) : a.emit("Tracker:Callback:Load:" + b + ":Error", {
                lvl: "ERROR",
                msg: "not a function",
                details: {obj: k}
            });
            return a
        };
        g.init = function () {
            if (a.getConfig("activateCallbacks")) {
                var n = a.getConfig("callbacks");
                if ("undefined" !== typeof n && n.include instanceof
                    Array) for (var k = 0; k < n.include.length; k++) ATInternet.Callbacks && ATInternet.Callbacks.hasOwnProperty(n.include[k]) && (m[n.include[k]] = {"function": ATInternet.Callbacks[n.include[k]]}, ATInternet.Tracker.callbackProtos[n.include[k]] || (ATInternet.Tracker.callbackProtos[n.include[k]] = m[n.include[k]])); else for (k in ATInternet.Callbacks) ATInternet.Callbacks.hasOwnProperty(k) && (m[k] = {"function": ATInternet.Callbacks[k]}, ATInternet.Tracker.callbackProtos[k] || (ATInternet.Tracker.callbackProtos[k] = m[k]));
                if ("undefined" !==
                    typeof n && n.exclude instanceof Array) for (k = 0; k < n.exclude.length; k++) m[n.exclude[k]] && (m[n.exclude[k]] = void 0);
                for (var e in m) m.hasOwnProperty(e) && m[e] && g.load(e, m[e]["function"]);
                ATInternet.Utils.addCallbackEvent(b)
            }
        };
        g.removeCallbackEvent = function () {
            ATInternet.Utils.removeCallbackEvent(b)
        }
    }, BufferManager = function (a) {
        var g = {};
        this.set = function (a, b, e) {
            e = e || {};
            e.hitType = e.hitType || ["page"];
            g[a] = {value: b, options: e}
        };
        var m = function (a, b, e) {
            return (a = window.ATInternet.Utils.cloneSimpleObject(a[b])) && !e ?
                a.value : a
        }, b = function k(a, b) {
            if (!(a && b instanceof Array && a instanceof Array)) return [];
            if (0 === a.length) return b;
            var h = a[0], f, c = [], d = window.ATInternet.Utils.cloneSimpleObject(a);
            d.shift();
            for (var p = 0; p < b.length; p++) if ("object" !== typeof h[1]) g[b[p]] && g[b[p]].options[h[0]] === h[1] && c.push(b[p]); else {
                f = h[1].length;
                for (var q = 0; q < f; q++) if (g[b[p]] && g[b[p]].options[h[0]] instanceof Array && 0 <= window.ATInternet.Utils.arrayIndexOf(g[b[p]].options[h[0]], h[1][q])) {
                    c.push(b[p]);
                    break
                }
            }
            return k(d, c)
        };
        this.get = function (a,
                             e) {
            var r = {};
            if ("string" === typeof a) r = m(g, a, e); else for (var h = b(a, window.ATInternet.Utils.getObjectKeys(g)), f = 0; f < h.length; f++) r[h[f]] = m(g, h[f], e);
            return r
        };
        this.presentInFilters = function e(a, b) {
            return a && 0 !== a.length ? a[0][0] === b ? !0 : e(a.slice(1), b) : !1
        };
        this.addInFilters = function r(a, b, c, d) {
            if (!a || 0 === a.length) return d ? [] : [[b, c]];
            var p = a[0][0], q = a[0][1];
            p === b && (q instanceof Array && -1 === window.ATInternet.Utils.arrayIndexOf(q, c[0]) && q.push(c[0]), d = !0);
            return [[p, q]].concat(r(a.slice(1), b, c, d))
        };
        this.del =
            function (a) {
                g[a] = void 0
            };
        this.clear = function () {
            g = {}
        }
    }, Tag = function (a, g, m) {
        g = g || {};
        var b = this;
        b.version = "5.18.1";
        var n = window.ATInternet.Utils.cloneSimpleObject(g);
        b.triggers = new TriggersManager(b);
        b.emit = b.triggers.emit;
        b.onTrigger = b.triggers.on;
        var k = window.ATInternet.Utils.cloneSimpleObject(dfltGlobalCfg) || {}, e;
        for (e in a) a.hasOwnProperty(e) && (k[e] = a[e]);
        b.getConfig = function (a) {
            return k[a]
        };
        b.setConfig = function (a, e, f) {
            void 0 !== k[a] && f || (b.emit("Tracker:Config:Set:" + a, {
                lvl: "INFO", details: {
                    bef: k[a],
                    aft: e
                }
            }), k[a] = e)
        };
        b.configPlugin = function (a, e, f) {
            k[a] = k[a] || {};
            for (var c in e) e.hasOwnProperty(c) && void 0 === k[a][c] && (k[a][c] = e[c]);
            f && (f(k[a]), b.onTrigger("Tracker:Config:Set:" + a, function (c, a) {
                f(a.details.aft)
            }));
            return k[a]
        };
        b.getContext = function (a) {
            return n[a]
        };
        b.setContext = function (a, e) {
            b.emit("Tracker:Context:Set:" + a, {lvl: "INFO", details: {bef: n[a], aft: e}});
            n[a] = e
        };
        b.delContext = function (a, e) {
            b.emit("Tracker:Context:Deleted:" + a + ":" + e, {lvl: "INFO", details: {key1: a, key2: e}});
            if (a) n.hasOwnProperty(a) &&
            (e ? n[a] && n[a].hasOwnProperty(e) && (n[a][e] = void 0) : n[a] = void 0); else if (e) for (var f in n) n.hasOwnProperty(f) && n[f] && n[f].hasOwnProperty(e) && (n[f][e] = void 0)
        };
        b.plugins = new PluginsManager(b);
        b.buffer = new BufferManager(b);
        b.setParam = b.buffer.set;
        b.getParams = function (a) {
            return b.buffer.get(a, !1)
        };
        b.getParam = b.buffer.get;
        b.delParam = b.buffer.del;
        b.builder = new BuildManager(b);
        b.sendHit = b.builder.send;
        b.sendUrl = b.builder.sendUrl;
        b.callbacks = new CallbacksManager(b);
        b.setParam("ts", function () {
                return (new Date).getTime()
            },
            {permanent: !0, hitType: ["all"]});
        (b.getConfig("disableCookie") || b.getConfig("disableStorage")) && b.setParam("idclient", "Consent-NO", {
            permanent: !0,
            hitType: ["all"]
        });
        b.getConfig("medium") && b.setParam("medium", b.getConfig("medium"), {permanent: !0, hitType: ["all"]});
        b.plugins.init();
        b.callbacks.init();
        ATInternet.Tracker.instances.push(b);
        b.emit("Tracker:Ready", {
            lvl: "INFO",
            msg: "Tracker initialized",
            details: {tracker: b, args: {config: a, context: g, callback: m}}
        });
        m && m(b)
    };
    ATInternet.Tracker.Tag = Tag;
    ATInternet.Tracker.instances = [];
    ATInternet.Tracker.pluginProtos = {};
    ATInternet.Tracker.addPlugin = function (a, g) {
        g = g || ATInternet.Tracker.Plugins[a];
        if (!ATInternet.Tracker.pluginProtos[a]) {
            ATInternet.Tracker.pluginProtos[a] = g;
            for (var m = 0; m < ATInternet.Tracker.instances.length; m++) ATInternet.Tracker.instances[m].plugins.load(a, g)
        }
    };
    ATInternet.Tracker.delPlugin = function (a) {
        if (ATInternet.Tracker.pluginProtos[a]) {
            ATInternet.Tracker.pluginProtos[a] = void 0;
            for (var g = 0; g < ATInternet.Tracker.instances.length; g++) ATInternet.Tracker.instances[g].plugins.unload(a)
        }
    };
    ATInternet.Tracker.callbackProtos = {};
}).call(window);
(function () {
    var dfltPluginCfg = {"lifetime": 30, "lastPersistence": true, "listEventsForExec": [], "domainAttribution": true};
    var dfltGlobalCfg = {"visitLifetime": 30, "redirectionLifetime": 30};
    window.ATInternet.Tracker.Plugins.Campaigns = function (a) {
        a.setConfig("visitLifetime", dfltGlobalCfg.visitLifetime, !0);
        a.setConfig("redirectionLifetime", dfltGlobalCfg.redirectionLifetime, !0);
        var g = {}, m, b;
        a.configPlugin("Campaigns", dfltPluginCfg || {}, function (a) {
            g = a
        });
        var n, k, e, r, h, f, c, d, p, q, l, w, s, u = function () {
            var c = function (c) {
                var a = "";
                c && (a = isNaN(c) && -1 === c.search(/\[(.*?)\]/g) ? "[" + c + "]" : c);
                return a
            }, d = function (c) {
                for (; "-" === c.charAt(c.length - 1);) c = c.substring(0, c.length - 1);
                return c
            };
            this.SponsoredLinks =
                function () {
                    var b = {
                        google: "goo",
                        yahoo: "ysm",
                        miva: "miv",
                        orange: "wan",
                        msn: "msn",
                        mirago: "mir",
                        sklik: "skl",
                        adfox: "adf",
                        etarget: "etg",
                        yandex: "yan",
                        ebay: "eba",
                        searchalliance: "sal",
                        bing: "bin",
                        naver: "nav",
                        baidu: "bdu",
                        qwant: "qwt",
                        waze: "waz",
                        amazon: "amz"
                    }, f = {search: "s", content: "c"};
                    this.at_medium = "sl";
                    this.at_term = this.at_network = this.at_variant = this.at_creation = this.at_platform = this.at_campaign = "";
                    this.format = function () {
                        var a = "sec", e = c(this.at_campaign), q = b[this.at_platform] || "", l = c(this.at_creation),
                            p = c(this.at_variant),
                            h = f[this.at_network] || "", k = c(this.at_term);
                        return d(a + ("-" + e + "-" + q + "-" + l + "-" + p + "-" + h + "-" + k))
                    };
                    this.setProperties = function (c) {
                        this.at_campaign = a.utils.getQueryStringValue("at_campaign", c) || "";
                        this.at_platform = a.utils.getQueryStringValue("at_platform", c) || "";
                        this.at_creation = a.utils.getQueryStringValue("at_creation", c) || "";
                        this.at_variant = a.utils.getQueryStringValue("at_variant", c) || "";
                        this.at_network = a.utils.getQueryStringValue("at_network", c) || "";
                        this.at_term = a.utils.getQueryStringValue("at_term",
                            c) || ""
                    }
                };
            this.Email = function () {
                var b = {acquisition: "erec", retention: "epr", promotion: "es"};
                this.at_medium = "email";
                this.at_send_time = this.at_recipient_list = this.at_recipient_id = this.at_link = this.at_send_date = this.at_creation = this.at_campaign = this.at_emailtype = "";
                this.format = function () {
                    var a = b[this.at_emailtype] || b.promotion, f = c(this.at_campaign), e = c(this.at_creation),
                        q = this.at_send_date, l = c(this.at_link),
                        a = a + ("-" + f + "-" + e + "-" + q + "-" + l + "-" + (this.at_recipient_id + (this.at_recipient_list ? "@" + this.at_recipient_list :
                            "")) + "-" + this.at_send_time);
                    return d(a)
                };
                this.setProperties = function (c) {
                    this.at_emailtype = a.utils.getQueryStringValue("at_emailtype", c) || "";
                    this.at_campaign = a.utils.getQueryStringValue("at_campaign", c) || "";
                    this.at_creation = a.utils.getQueryStringValue("at_creation", c) || "";
                    this.at_send_date = a.utils.getQueryStringValue("at_send_date", c) || "";
                    this.at_link = a.utils.getQueryStringValue("at_link", c) || "";
                    this.at_recipient_id = a.utils.getQueryStringValue("at_recipient_id", c) || "";
                    this.at_recipient_list = a.utils.getQueryStringValue("at_recipient_list",
                        c) || "";
                    this.at_send_time = a.utils.getQueryStringValue("at_send_time", c) || ""
                }
            };
            this.Affiliate = function () {
                this.at_medium = "affiliate";
                this.at_variant = this.at_creation = this.at_format = this.at_identifier = this.at_type = this.at_campaign = "";
                this.format = function () {
                    var a = "al", b = c(this.at_campaign), f = c(this.at_type), e = c(this.at_identifier),
                        q = c(this.at_format), l = c(this.at_creation), p = c(this.at_variant);
                    return d(a + ("-" + b + "-" + f + "-" + e + "-" + q + "-" + l + "-" + p))
                };
                this.setProperties = function (c) {
                    this.at_campaign = a.utils.getQueryStringValue("at_campaign",
                        c) || "";
                    this.at_type = a.utils.getQueryStringValue("at_type", c) || "";
                    this.at_identifier = a.utils.getQueryStringValue("at_identifier", c) || "";
                    this.at_format = a.utils.getQueryStringValue("at_format", c) || "";
                    this.at_creation = a.utils.getQueryStringValue("at_creation", c) || "";
                    this.at_variant = a.utils.getQueryStringValue("at_variant", c) || ""
                }
            };
            this.Display = function () {
                this.at_medium = "display";
                this.at_detail_placement = this.at_general_placement = this.at_channel = this.at_format = this.at_variant = this.at_creation = this.at_campaign =
                    "";
                this.format = function () {
                    var a = "ad", b = c(this.at_campaign), f = c(this.at_creation), e = c(this.at_variant),
                        q = c(this.at_format), l = c(this.at_channel), p = c(this.at_general_placement),
                        h = c(this.at_detail_placement);
                    return d(a + ("-" + b + "-" + f + "-" + e + "-" + q + "-" + l + "-" + p + "-" + h))
                };
                this.setProperties = function (c) {
                    this.at_campaign = a.utils.getQueryStringValue("at_campaign", c) || "";
                    this.at_creation = a.utils.getQueryStringValue("at_creation", c) || "";
                    this.at_variant = a.utils.getQueryStringValue("at_variant", c) || "";
                    this.at_format =
                        a.utils.getQueryStringValue("at_format", c) || "";
                    this.at_channel = a.utils.getQueryStringValue("at_channel", c) || "";
                    this.at_general_placement = a.utils.getQueryStringValue("at_general_placement", c) || "";
                    this.at_detail_placement = a.utils.getQueryStringValue("at_detail_placement", c) || ""
                }
            };
            this.Custom = function () {
                this.at_custom4 = this.at_custom3 = this.at_custom2 = this.at_custom1 = this.at_campaign = this.at_medium = "";
                this.format = function () {
                    var a = "";
                    /\d+$/.test(this.at_medium) && (a = /\d+$/.exec(this.at_medium)[0]);
                    var a =
                        "cs" + a, b = c(this.at_campaign), f = c(this.at_custom1), e = c(this.at_custom2),
                        q = c(this.at_custom3), l = c(this.at_custom4);
                    return d(a + ("-" + b + "-" + f + "-" + e + "-" + q + "-" + l))
                };
                this.setProperties = function (c) {
                    this.at_medium = a.utils.getQueryStringValue("at_medium", c) || "";
                    this.at_campaign = a.utils.getQueryStringValue("at_campaign", c) || "";
                    this.at_custom1 = a.utils.getQueryStringValue("at_custom1", c) || "";
                    this.at_custom2 = a.utils.getQueryStringValue("at_custom2", c) || "";
                    this.at_custom3 = a.utils.getQueryStringValue("at_custom3",
                        c) || "";
                    this.at_custom4 = a.utils.getQueryStringValue("at_custom4", c) || ""
                }
            };
            this.medium = {sl: this.SponsoredLinks, email: this.Email, affiliate: this.Affiliate, display: this.Display}
        }, v = function (c, d) {
            var f = a.storage[b](c);
            if (null !== f) return "object" === typeof f && !(f instanceof Array);
            a.storage[m](c, {}, d);
            return !0
        }, x = function (c, d) {
            var b = a.getContext("campaigns") || {};
            b[c] = d;
            a.setContext("campaigns", b)
        };
        (function () {
            a.plugins.waitForDependencies(["Storage", "Utils"], function () {
                m = "set" + (g.domainAttribution ? "" : "Private");
                b = "get" + (g.domainAttribution ? "" : "Private");
                n = a.storage[b](["atredir", "gopc"]);
                k = a.storage[b](["atredir", "gopc_err"]);
                e = a.storage[b](["atredir", "camp"]);
                a.storage.del(["atredir", "gopc"]);
                a.storage.del(["atredir", "gopc_err"]);
                a.storage.del(["atredir", "camp"]);
                r = a.storage[b](["atsession", "histo_camp"]);
                h = a.storage[b](["atreman", "camp"]);
                f = a.storage[b](["atreman", "date"]);
                var t = a.utils.getLocation(), y = a.utils.getQueryStringValue("at_medium", t);
                if (y) {
                    var A = new u, y = "function" === typeof A.medium[y] ? new A.medium[y] :
                        new A.Custom;
                    y.setProperties(t);
                    c = y.format()
                } else c = a.utils.getQueryStringValue("xtor", t);
                d = a.utils.getQueryStringValue("xtdt", t);
                p = a.utils.getQueryStringValue("xts", t);
                q = a.getContext("forcedCampaign");
                l = !!a.getConfig("redirect");
                w = !!(c && d && p);
                s = !1;
                w && (t = (new Date).getTime() / 6E4, s = !l && p !== a.getConfig("site") || 0 > t - d || t - d >= a.getConfig("visitLifetime"));
                t = q || e || c;
                l && t && v("atredir", {
                    path: "/",
                    end: a.getConfig("redirectionLifetime")
                }) && (a.storage[m](["atredir", "camp"], t), y = t = !1, q || (e ? (t = n, y = k) : (t = w, y = s)),
                    a.storage[m](["atredir", "gopc"], t), a.storage[m](["atredir", "gopc_err"], y));
                !l && h && (x("xtor", h), t = (new Date).getTime() / 36E5, t = Math.floor(t - f), x("roinbh", 0 <= t ? t : 0));
                l || (t = null, t = e ? n ? q || t : q || e : w ? q : q || c || t, r && r instanceof Array && -1 < r.indexOf(t) && (t = null), t && x("xto", t));
                if (!l && !q) {
                    var z;
                    e ? k && (z = e) : s && (z = c);
                    z && x("pgt", z)
                }
                if (!l && (z = e ? q || e : q || c || null) && !(!q && !e && w && s || !q && e && n && k)) {
                    if ((!r || r instanceof Array && 0 > r.indexOf(z)) && v("atsession", {
                        path: "/",
                        session: 60 * a.getConfig("visitLifetime")
                    })) a.storage[m](["atsession",
                        "histo_camp"], r && r.push(z) ? r : [z]);
                    h && !g.lastPersistence || !v("atreman", {
                        path: "/",
                        session: 86400 * g.lifetime
                    }) || (a.storage[m](["atreman", "camp"], z), a.storage[m](["atreman", "date"], (new Date).getTime() / 36E5))
                }
                a.emit("Campaigns:process:done", {lvl: "INFO"})
            })
        })()
    };
    window.ATInternet.Tracker.addPlugin("Campaigns");
}).call(window);
(function () {
    var dfltPluginCfg = {};
    var dfltGlobalCfg = {"storageMode": "cookie"};
    ATInternet.Tracker.Plugins.Cookies = ATInternet.Tracker.Plugins.Storage = function (a) {
        var g = this, m = {}, b = !1, n = null;
        a.configPlugin("Storage", dfltPluginCfg || {}, function (a) {
            m = a;
            "localStorage" === m.storageMode && (b = ATInternet.Utils.isLocalStorageAvailable())
        });
        var k = {}, e = function (c) {
            return a.getConfig("base64Storage") ? ATInternet.Utils.Base64.encode(c) : encodeURIComponent(c)
        }, r = function (c) {
            return a.getConfig("base64Storage") ? ATInternet.Utils.Base64.decode(c) : decodeURIComponent(c)
        }, h = function () {
            this.getData = function (a) {
                var c =
                    null;
                (a = RegExp("(?:^| )" + a + "=([^;]+)").exec(document.cookie) || null) && (c = r(a[1]));
                return c
            };
            this.setData = function (c) {
                var b = !1;
                if (c.name && "string" === typeof c.name) {
                    var d = c.options || {}, f = d.end || {}, g = d.domain || a.getConfig("cookieDomain"),
                        h = d.secure || a.getConfig("cookieSecure"), k = ATInternet.Utils.jsonSerialize(c),
                        k = c.name + "=" + e(k),
                        k = k + (d.path && "string" === typeof d.path ? ";path=" + d.path : ""),
                        k = k + (g && "string" === typeof g ? ";domain=" + g : "") + (h && "boolean" === typeof h ? ";secure" : "");
                    "function" === typeof f.toUTCString ?
                        k += ";expires=" + f.toUTCString() : "number" === typeof f && (k += ";max-age=" + f.toString());
                    document.cookie = k;
                    this.getData(c.name) && (b = !0)
                }
                return b
            }
        };
        n = b ? new function () {
                var a = function (a) {
                    var c = +new Date, b = !1, d;
                    a.options && ("undefined" !== typeof a.options.expires ? d = a.options.expires : (a = a.options.end || {}, "function" === typeof a.getTime ? d = a.getTime() : "number" === typeof a && (d = c + 1E3 * a)));
                    "number" === typeof d && c >= d && (b = !0);
                    return {itemToDelete: b, timestamp: d}
                }, c = function (a) {
                    var c = !1;
                    try {
                        localStorage.removeItem(a), c = !0
                    } catch (b) {
                    }
                    return c
                };
                this.getData = function (b) {
                    var d = null, e = localStorage.getItem(b);
                    if (e) {
                        var e = r(e), f = ATInternet.Utils.jsonParse(e);
                        f && "object" === typeof f ? a(f).itemToDelete && c(b) || (delete f.options.expires, d = ATInternet.Utils.jsonSerialize(f)) : d = e
                    }
                    return d
                };
                this.setData = function (b) {
                    var d = !1;
                    if (b.name && "string" === typeof b.name) {
                        var f = a(b);
                        "number" === typeof f.timestamp && (b.options.expires = f.timestamp);
                        var g = ATInternet.Utils.jsonSerialize(b);
                        if (f.itemToDelete) d = c(b.name); else try {
                            localStorage.setItem(b.name, e(g)), d = !0
                        } catch (h) {
                        }
                    }
                    return d
                }
            } :
            new h;
        var f = function (c, b) {
            var d = !1;
            !ATInternet.Utils.consent && !b || a.getConfig("disableCookie") || a.getConfig("disableStorage") || !c || "object" !== typeof c || (d = n.setData(c));
            return d
        }, c = function (a, c, b) {
            a = {name: a, val: c};
            b && b.session && "number" === typeof b.session && (b.end = b.session);
            a.options = b || {};
            return a
        }, d = function (c) {
            var b = null, d = null;
            a.getConfig("disableCookie") || a.getConfig("disableStorage") || !c || "string" !== typeof c || (d = n.getData(c));
            (c = d) && (b = ATInternet.Utils.jsonParse(c));
            return b
        }, p = function (a, c) {
            var b =
                ATInternet.Utils.cloneSimpleObject(a);
            return f(b, c) ? ATInternet.Utils.jsonParse(ATInternet.Utils.jsonSerialize(a)) : null
        }, q = function (a, c, b) {
            if (!b && k[a]) b = k[a]; else if (b = d(a)) b.options = b.options || {}, b.options.session && "number" === typeof b.options.session && (b.options.end = b.options.session, p(b, !1)), k[a] = b;
            return b ? c ? (a = null, !b || "object" !== typeof b.val || b.val instanceof Array || void 0 === b.val[c] || (a = b.val[c]), a) : b.val : null
        }, l = function (a, b, e, f, g) {
            if (b) {
                if (g = d(a)) !g || "object" !== typeof g.val || g.val instanceof
                Array ? g = null : "undefined" === typeof e ? delete g.val[b] : g.val[b] = e, g && (g = p(g, f))
            } else g = g || {}, g = c(a, e, g), g = p(g, f);
            return g ? (k[a] = g, g.val) : null
        }, w = function (a, b) {
            if (b) l(a, b, void 0, !1, null); else {
                k[a] = void 0;
                var d = c(a, "", {end: new Date("Thu, 01 Jan 1970 00:00:00 UTC"), path: "/"});
                f(d, !1)
            }
        };
        a.storage = {};
        a.storage.get = g.get = function (a, c) {
            c = !!c;
            return a instanceof Array ? q(a[0], a[1], c) : q(a, "", c)
        };
        a.storage.getPrivate = g.getPrivate = function (c, b) {
            c instanceof Array ? c[0] += a.getConfig("site") : c += a.getConfig("site");
            return g.get(c, b)
        };
        a.storage.set = g.set = function (a, c, b, d) {
            return a instanceof Array ? l(a[0], a[1], c, d, null) : l(a, null, c, d, b)
        };
        a.storage.setPrivate = g.setPrivate = function (c, b, d) {
            c instanceof Array ? c[0] += a.getConfig("site") : c += a.getConfig("site");
            return g.set(c, b, d)
        };
        a.storage.del = g.del = function (a) {
            a instanceof Array ? w(a[0], a[1]) : w(a, "")
        };
        a.storage.delPrivate = g.delPrivate = function (c) {
            c instanceof Array ? c[0] += a.getConfig("site") : c += a.getConfig("site");
            g.del(c)
        };
        a.storage.cacheInvalidation = g.cacheInvalidation =
            function () {
                k = {}
            }
    };
    ATInternet.Tracker.addPlugin("Storage");
    ATInternet.Tracker.addPlugin("Cookies");
}).call(window);
(function () {
    var dfltPluginCfg = {};
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.Utils = function (a) {
        var g = this, m = {};
        a.utils = {};
        a.utils.getQueryStringValue = g.getQueryStringValue = function (a, b) {
            var g = ATInternet.Utils.hashcode(b).toString();
            if (!m[g]) {
                m[g] = {};
                var h = RegExp("[&#?]{1}([^&=#?]*)=([^&#]*)?", "g"), f = h.exec(b);
                if (null !== f) for (; null !== f;) m[g][f[1]] = f[2], f = h.exec(b)
            }
            return m[g].hasOwnProperty(a) ? m[g][a] : null
        };
        a.utils.manageChapters = g.manageChapters = function (b, e, g) {
            var h = a.getConfig("ignoreEmptyChapterValue"), f = "";
            if (b) {
                g = parseInt(g, 10);
                for (var c =
                    1; c < g + 1; c++) var d = b[e + c] || "", f = h ? f + (d ? d + "::" : "") : f + (b.hasOwnProperty(e + c) ? d + "::" : "")
            }
            return f
        };
        a.utils.getDocumentLevel = g.getDocumentLevel = function () {
            var b = a.getConfig("documentLevel");
            if (b) {
                if (0 > b.indexOf(".")) return window[b] || document;
                b = b.split(".");
                return window[b[0]][b[1]] || document
            }
            return document
        };
        a.utils.getLocation = g.getLocation = function () {
            return g.getDocumentLevel().location.href
        };
        a.utils.getHostName = g.getHostName = function () {
            return g.getDocumentLevel().location.hostname
        };
        a.utils.getCollectDomain =
            g.getCollectDomain = function () {
                var b = "", b = a.getConfig("logSSL") || a.getConfig("log"), e = a.getConfig("domain");
                return b = b && e ? b + "." + e : a.getConfig("collectDomainSSL") || a.getConfig("collectDomain")
            };
        a.dispatchIndex = {};
        a.dispatchStack = [];
        a.dispatchEventFor = {};
        var b = 0;
        a.dispatchSubscribe = function (b) {
            return a.dispatchIndex[b] ? !1 : (a.dispatchStack.push(b), a.dispatchIndex[b] = !0)
        };
        a.dispatchSubscribed = function (b) {
            return !0 === a.dispatchIndex[b]
        };
        a.addSpecificDispatchEventFor = function (g) {
            return a.dispatchEventFor[g] ?
                !1 : (a.dispatchEventFor[g] = !0, b++, !0)
        };
        a.processSpecificDispatchEventFor = function (g) {
            a.dispatchEventFor[g] && (a.dispatchEventFor[g] = !1, b--, 0 === b && (a.dispatchEventFor = {}, a.emit("Tracker:Plugin:SpecificEvent:Exec:Complete", {lvl: "INFO"})))
        };
        a.dispatch = function (g) {
            var e = function () {
                for (var b = "", e = null; 0 < a.dispatchStack.length;) b = a.dispatchStack.pop(), 0 === a.dispatchStack.length && (e = g), a[b].onDispatch(e);
                a.dispatchIndex = {};
                a.delContext(void 0, "customObject")
            }, m = function () {
                if (a.plugins.isExecWaitingLazyloading()) a.onTrigger("Tracker:Plugin:Lazyload:Exec:Complete",
                    function () {
                        e()
                    }, !0); else e()
            };
            if (0 === b) m(); else a.onTrigger("Tracker:Plugin:SpecificEvent:Exec:Complete", function () {
                m()
            }, !0)
        };
        a.dispatchRedirect = function (b) {
            var e = !0, g = null;
            b && (g = null, b && b.hasOwnProperty("event") && (g = b.event || window.event), !ATInternet.Utils.isTabOpeningAction(g) && b.elem && (b.elem.timeoutonly = !0, a.plugins.exec("TechClicks", "manageClick", [b.elem, g], function (a) {
                e = a
            })), g = b.callback);
            a.dispatch(g);
            return e
        };
        var n = a.manageSend = function (b) {
            if (!ATInternet.Utils.isPreview() || a.getConfig("preview")) ATInternet.Utils.isPrerender(function (a) {
                b(a)
            }) ||
            b()
        };
        a.processTagObject = function (b, e, g) {
            if ((b = a.getParam(b, !0)) && b.options.permanent) {
                for (var h = !1, f = b.options.hitType || [], c = 0; c < f.length; c++) if (-1 !== ATInternet.Utils.arrayIndexOf(e.concat("all"), f[c])) {
                    h = !0;
                    break
                }
                h && (g = ATInternet.Utils.completeFstLevelObj(b.value || {}, g, !0))
            }
            return g
        };
        a.processContextObjectAndSendHit = function (b, e, g, h) {
            var f = a.getParam(b, !0);
            if (f) {
                for (var c = !1, d = f.options.hitType || [], m = 0; m < d.length; m++) if (-1 !== ATInternet.Utils.arrayIndexOf(e.hitType.concat("all"), d[m])) {
                    c = !0;
                    break
                }
                c ?
                    (c = ATInternet.Utils.cloneSimpleObject(f), c.value = ATInternet.Utils.completeFstLevelObj(c.value || {}, g, !0), a.setParam(b, c.value, {
                        hitType: e.hitType,
                        encode: e.encode,
                        separator: e.separator,
                        truncate: e.truncate
                    }), n(function () {
                        a.sendHit(null, [["hitType", e.hitType]], h, null, !0)
                    }), f.options.permanent && a.setParam(b, f.value, f.options)) : (a.setParam(b, g, {
                        hitType: e.hitType,
                        encode: e.encode,
                        separator: e.separator,
                        truncate: e.truncate
                    }), n(function () {
                        a.sendHit(null, [["hitType", e.hitType]], h, null, !0)
                    }), a.setParam(b, f.value,
                    f.options))
            } else a.setParam(b, g, {
                hitType: e.hitType,
                encode: e.encode,
                separator: e.separator,
                truncate: e.truncate
            }), n(function () {
                a.sendHit(null, [["hitType", e.hitType]], h, null, !0)
            })
        }
    };
    window.ATInternet.Tracker.addPlugin("Utils");
}).call(window);
(function () {
    var dfltPluginCfg = {"clicksAutoManagementEnabled": true, "clicksAutoManagementTimeout": 500};
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.TechClicks = function (a) {
        var g = this, m, b;
        a.configPlugin("TechClicks", dfltPluginCfg || {}, function (a) {
            m = a.clicksAutoManagementEnabled;
            b = a.clicksAutoManagementTimeout
        });
        g.deactivateAutoManagement = function () {
            m = !1
        };
        var n = function (a) {
            switch (a.target) {
                case "_top":
                    window.top.location.href = a.url;
                    break;
                case "_parent":
                    window.parent.location.href = a.url;
                    break;
                default:
                    window.location.href = a.url
            }
        }, k = function (a) {
            var b = a.timeout;
            a.mailto ? g.timeout = setTimeout(function () {
                window.location.href =
                    a.mailto
            }, b) : a.form ? g.timeout = setTimeout(function () {
                a.form.submit()
            }, b) : a.url && (g.timeout = setTimeout(function () {
                n({url: a.url, target: a.target})
            }, b))
        }, e = function (c) {
            for (var d, e = "_self", f = c.timeoutonly; c;) {
                if (c.href && 0 === c.href.indexOf("http")) {
                    d = c.href.split('"').join('\\"');
                    e = c.target ? c.target : e;
                    break
                }
                c = c.parentNode
            }
            if (d) {
                if (!f) a.onTrigger("Tracker:Hit:Sent:Ok", function () {
                    g.timeout && clearTimeout(g.timeout);
                    n({url: d, target: e})
                });
                k({url: d, target: e, timeout: b})
            }
        }, r = function (c) {
            var d = c;
            for (c = d.timeoutonly; d &&
            "FORM" !== d.nodeName;) d = d.parentNode;
            if (d) {
                if (!c) a.onTrigger("Tracker:Hit:Sent:Ok", function () {
                    g.timeout && clearTimeout(g.timeout);
                    d.submit()
                });
                k({form: d, timeout: b})
            }
        }, h = function (c) {
            var d = c;
            for (c = d.timeoutonly; d && !(d.href && 0 <= d.href.indexOf("mailto:"));) d = d.parentNode;
            if (d) {
                if (!c) a.onTrigger("Tracker:Hit:Sent:Ok", function () {
                    g.timeout && clearTimeout(g.timeout);
                    window.location.href = d.href
                });
                k({mailto: d.href, timeout: b})
            }
        }, f = function (a) {
            for (; a;) {
                if (a.href) {
                    if (0 <= a.href.indexOf("mailto:")) return "mailto";
                    if (0 ===
                        a.href.indexOf("http")) return "redirection"
                } else if ("FORM" === a.nodeName) return "form";
                a = a.parentNode
            }
            return ""
        };
        a.techClicks = {};
        a.techClicks.manageClick = g.manageClick = function (a, b) {
            var g = !0;
            if (m && a) {
                var k;
                a:{
                    for (k = a; k;) {
                        if ("function" === typeof k.getAttribute && ("_blank" === k.getAttribute("target") || "no" === k.getAttribute("data-atclickmanagement"))) {
                            k = !0;
                            break a
                        }
                        k = k.parentNode
                    }
                    k = a;
                    for (var l = window.location.href, n; k;) {
                        if ((n = k.href) && 0 <= n.indexOf("#") && l.substring(0, 0 <= l.indexOf("#") ? l.indexOf("#") : l.length) ===
                            n.substring(0, n.indexOf("#"))) {
                            k = !0;
                            break a
                        }
                        k = k.parentNode
                    }
                    k = !1
                }
                l = f(a);
                if (!k && l) {
                    switch (l) {
                        case "mailto":
                            h(a);
                            g = !1;
                            break;
                        case "form":
                            r(a);
                            g = !1;
                            break;
                        case "redirection":
                            e(a), g = !1
                    }
                    b && (k = b.defaultPrevented, "function" === typeof b.isDefaultPrevented && (k = b.isDefaultPrevented()), k || b.preventDefault && b.preventDefault())
                }
            }
            return g
        }
    };
    window.ATInternet.Tracker.addPlugin("TechClicks");
}).call(window);
(function () {
    var dfltPluginCfg = {};
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.Clicks = function (a) {
        var g = function (a) {
            var b = "";
            switch (a) {
                case "exit":
                    b = "S";
                    break;
                case "download":
                    b = "T";
                    break;
                case "action":
                    b = "A";
                    break;
                case "navigation":
                    b = "N"
            }
            return b
        }, m = function (b) {
            return a.utils.manageChapters(b, "chapter", 3) + (b.name ? b.name : "")
        }, b = function (b) {
            var k = {p: m(b), s2: b.level2 || "", click: g(b.type) || ""}, e = ["click"],
                r = a.getContext("page") || {};
            k.pclick = m(r);
            k.s2click = r.level2 || "";
            var r = b.customObject, h = !1;
            r && (h = !0, r = a.processTagObject("stc", e, r), k.stc = {
                value: ATInternet.Utils.jsonSerialize(r),
                options: {hitType: e, encode: !0, separator: ",", truncate: !0}
            });
            a.sendHit(k, [["hitType", e]], b.callback, null, h)
        };
        a.click = {};
        a.clickListener = this.clickListener = {};
        a.click.send = this.send = function (g) {
            var k = !0, e = null;
            g && g.hasOwnProperty("event") && (e = g.event || window.event);
            !ATInternet.Utils.isTabOpeningAction(e) && g.elem && (k = a.techClicks.manageClick(g.elem, e));
            b(g);
            return k
        };
        a.clickListener.send = this.clickListener.send = function (g) {
            if (g.elem) {
                var k = "click";
                a.plugins.exec("TechClicks", "isFormSubmit", [g.elem], function (a) {
                    k =
                        a ? "submit" : "click"
                });
                ATInternet.Utils.addEvtListener(g.elem, k, function (e) {
                    ATInternet.Utils.isTabOpeningAction(e) || a.techClicks.manageClick(g.elem, e);
                    b(g)
                })
            }
        };
        a.click.set = this.set = function (b) {
            a.dispatchSubscribe("click");
            a.setContext("click", {name: m(b), level2: b.level2 || "", customObject: b.customObject});
            a.setParam("click", g(b.type) || "", {hitType: ["click"]})
        };
        a.click.onDispatch = this.onDispatch = function (b) {
            var k = ["click"], e = a.getContext("click") || {}, g = a.getContext("page") || {};
            a.setParam("pclick", m(g), {hitType: k});
            a.setParam("s2click", g.level2 || "", {hitType: k});
            a.setParam("p", e.name, {hitType: k});
            a.setParam("s2", e.level2, {hitType: k});
            (e = e.customObject) ? a.processContextObjectAndSendHit("stc", {
                hitType: k,
                encode: !0,
                separator: ",",
                truncate: !0
            }, e, b) : a.manageSend(function () {
                a.sendHit(null, [["hitType", k]], b)
            });
            a.setContext("click", void 0)
        }
    };
    window.ATInternet.Tracker.addPlugin("Clicks");
}).call(window);
(function () {
    var dfltPluginCfg = {
        "clientSideMode": "always",
        "userIdCookieDuration": 397,
        "userIdExpirationMode": "fixed",
        "optOut": "OPT-OUT",
        "userIdStorageName": "atuserid",
        "optOutStorageName": "atoptedout",
        "itpCompliant": false,
        "baseDomain": ""
    };
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.ClientSideUserId = function (a) {
        var g = {}, m = !1, b = void 0, n = null, k = !1, e = !1, r = !1, h = null, f = "", c = !1, d = !1, p = -1;
        a.configPlugin("ClientSideUserId", dfltPluginCfg || {}, function (c) {
            g = c
        });
        var q = function () {
            var c = g.baseDomain;
            if (!c) {
                var d = a.getConfig("cookieDomain");
                d && (c = d, "." === c.charAt(0) && (c = c.substring(1, c.length)))
            }
            var d = a.utils.getCollectDomain(), b = a.utils.getHostName();
            return c && d && b && -1 < d.indexOf(c) && -1 < b.indexOf(c) ? (a.setConfig("secure", !0), a.setConfig("logSSL", a.getConfig("logSSL") ||
                a.getConfig("log")), a.setConfig("collectDomainSSL", a.getConfig("collectDomainSSL") || a.getConfig("collectDomain")), !0) : !1
        }, l = function () {
            if ("relative" === g.userIdExpirationMode || "fixed" === g.userIdExpirationMode && null === n || c) {
                var b = new Date;
                b.setTime(b.getTime() + 864E5 * g.userIdCookieDuration);
                a.storage.set(f, h, {end: b, path: "/"}, d);
                b = a.storage.get(f, !0);
                ATInternet.Utils.consent && !c && h !== b && a.setParam("idclient", h + "-NO", {
                    multihit: !0,
                    permanent: !0,
                    hitType: ["all"]
                })
            }
        }, w = function () {
            a.setParam("idclient", h, {
                multihit: !0,
                permanent: !0, hitType: ["all"]
            });
            l()
        }, s = function () {
            b = a.getContext("userIdentifier");
            n = a.storage.get("atuserid");
            var l = !1;
            if ("required" === g.clientSideMode) {
                var p = "";
                window.navigator && (p = window.navigator.userAgent);
                if (/Safari/.test(p) && !/Chrome/.test(p) || /iPhone|iPod|iPad/.test(p)) l = !0
            } else "always" === g.clientSideMode && (l = !0);
            k = l;
            l = !1;
            p = ATInternet.Utils.optedOut;
            !1 === p && (a.storage.del("atoptedout"), a.getParam("idclient") === g.optOut && a.delParam("idclient"));
            var s = a.storage.get("atoptedout", !0);
            if (!0 ===
                p || s === g.optOut) l = !0;
            l && (ATInternet.Utils.optedOut = !0);
            e = l;
            r = "undefined" !== typeof b;
            l = !1;
            if (g.itpCompliant && !r && !e) switch (g.clientSideMode) {
                case "never":
                    l = q();
                    break;
                case "always":
                case "required":
                    k && null !== n || (l = q())
            }
            m = l;
            !m && (k || e || r) ? (a.setConfig("userIdOrigin", "client"), f = g.userIdStorageName, d = c = !1, e ? (h = g.optOut, f = g.optOutStorageName, d = c = !0) : a.getConfig("disableCookie") || a.getConfig("disableStorage") ? (h = a.getParam("idclient"), c = !0) : r ? (h = b, c = !0) : h = null !== n ? n : ATInternet.Utils.uuid().v4(), w()) : a.setConfig("userIdOrigin",
                "server")
        }, u = function (c) {
            c && (c = c.detail) && "clientsideuserid" === c.name && c.id === p && s()
        };
        (function () {
            a.plugins.waitForDependencies(["Storage", "Utils"], function () {
                var c = ATInternet.Utils.uuid();
                p = parseInt(c.num(8));
                ATInternet.Utils.removeOptOutEvent(u);
                ATInternet.Utils.addOptOutEvent(p, u);
                s()
            })
        })();
        a.clientSideUserId = {};
        a.clientSideUserId.set = function (a) {
            e || (h = a, c = !0, f = g.userIdStorageName, d = !1, w())
        };
        a.clientSideUserId.store = function () {
            d = c = !0;
            l()
        };
        a.clientSideUserId.get = function () {
            return h
        }
    };
    window.ATInternet.Tracker.addPlugin("ClientSideUserId");
}).call(window);
(function () {
    var dfltPluginCfg = {"domainAttribution": true};
    var dfltGlobalCfg = {"redirectionLifetime": 30};
    window.ATInternet.Tracker.Plugins.ContextVariables = function (a) {
        var g = "", m = null, b, n = "", k = "", e = {};
        a.configPlugin("ContextVariables", dfltPluginCfg || {}, function (c) {
            e = c
        });
        a.setConfig("redirectionLifetime", dfltGlobalCfg.redirectionLifetime, !0);
        var r = function (c, d) {
            var b = null;
            a.plugins.exec("Storage", c, d, function (c) {
                b = c
            });
            return b
        }, h = function () {
            a.setParam("hl", function () {
                var c = new Date;
                return c.getHours() + "x" + c.getMinutes() + "x" + c.getSeconds()
            }, {permanent: !0, hitType: ["all"]})
        }, f = function (c) {
            (c = b ? b : "acc_dir" ===
            g ? "" : null !== g ? g : "acc_dir" === m ? "" : m ? m : c ? c.referrer : "") && (c = c.replace(/[<>]/g, "").substring(0, 1600).replace(/&/g, "$"));
            return c
        };
        a.plugins.waitForDependencies(["Storage", "Utils"], function () {
            n = "set" + (e.domainAttribution ? "" : "Private");
            k = "get" + (e.domainAttribution ? "" : "Private");
            var c = a.utils.getLocation();
            g = a.utils.getQueryStringValue("xtref", c);
            void 0 === g && (g = "");
            b = a.getContext("forcedReferer");
            if (a.getConfig("redirect")) {
                var c = a.utils.getDocumentLevel(), c = b ? b : null !== g ? g : c ? c.referrer : "acc_dir", d;
                if (d =
                    c) {
                    d = {path: "/", end: a.getConfig("redirectionLifetime")};
                    var p = r(k, ["atredir"]);
                    null !== p ? d = "object" === typeof p && !(p instanceof Array) : (r(n, ["atredir", {}, d]), d = !0)
                }
                d && r(n, [["atredir", "ref"], c])
            } else {
                m = r(k, [["atredir", "ref"]]);
                r("del", [["atredir", "ref"]]);
                a.setParam("vtag", a.version, {permanent: !0, hitType: ["all"]});
                a.setParam("ptag", "js", {permanent: !0, hitType: ["all"]});
                c = "";
                try {
                    c += window.screen.width + "x" + window.screen.height + "x" + window.screen.pixelDepth + "x" + window.screen.colorDepth
                } catch (q) {
                }
                a.setParam("r",
                    c, {permanent: !0, hitType: ["all"]});
                c = "";
                window.innerWidth ? c += window.innerWidth + "x" + window.innerHeight : document.body && document.body.offsetWidth && (c += document.body.offsetWidth + "x" + document.body.offsetHeight);
                a.setParam("re", c, {permanent: !0, hitType: ["all"]});
                h();
                window.navigator && a.setParam("lng", window.navigator.language || window.navigator.userLanguage, {
                    permanent: !0,
                    hitType: ["all"]
                });
                c = ATInternet.Utils.uuid().num(13);
                a.setParam("idp", c, {permanent: !0, hitType: ["page", "clickzone"]});
                window.navigator && a.setParam("jv",
                    window.navigator.javaEnabled() ? "1" : "0", {hitType: ["page"]});
                c = a.utils.getDocumentLevel();
                a.setParam("ref", f(c), {permanent: !0, last: !0, hitType: ["page", "ecommerce"]})
            }
            a.emit("ContextVariables:Ready", {lvl: "INFO"})
        })
    };
    window.ATInternet.Tracker.addPlugin("ContextVariables");
}).call(window);
(function () {
    var dfltPluginCfg = {};
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.Page = function (a) {
        var g = ["pageId", "chapterLabel", "update"], m = ["pid", "pchap", "pidt"], b = ["page", "site"],
            n = ["f", "x"], k = function (c) {
                return a.utils.manageChapters(c, "chapter", 3) + (c.name ? c.name : "")
            }, e = function (a, c, b) {
                c ? a = c : a || "undefined" === typeof b || (a = b);
                return a
            }, r = function (a, c, b) {
                c.hasOwnProperty(b) && (a[b] = e(a[b], c[b]))
            }, h = function (c, d, e) {
                if (d) for (var f = 0; f < b.length; f++) if (d.hasOwnProperty(b[f]) && d[b[f]]) for (var g in d[b[f]]) d[b[f]].hasOwnProperty(g) && (e ? c[n[f] + g] = d[b[f]][g] :
                    a.setParam(n[f] + g, d[b[f]][g]))
            }, f = function (c, b, d) {
                if (b) {
                    var e = a.utils.manageChapters(b, "chapter", 3);
                    e && (b.chapterLabel = e.replace(/::$/gi, ""));
                    for (e = 0; e < m.length; e++) b.hasOwnProperty(g[e]) && (d ? c[m[e]] = b[g[e]] : a.setParam(m[e], b[g[e]]))
                }
            }, c = function (c, b, d) {
                if (b && b.keywords instanceof Array) {
                    var e = b.keywords.length;
                    if (0 < e) {
                        for (var f = "", g = 0; g < e; g++) f += "[" + b.keywords[g] + "]" + (g < e - 1 ? "|" : "");
                        d ? c.tag = f : a.setParam("tag", f)
                    }
                }
            }, d = function (c, b, d) {
                if (b) {
                    var e, f = function (a) {
                        return a ? a : "0"
                    };
                    e = "" + (f(b.category1) + "-");
                    e += f(b.category2) + "-";
                    e += f(b.category3);
                    d ? c.ptype = e : a.setParam("ptype", e)
                }
            }, p = function (c, b, d) {
                if (b) for (var e in b) b.hasOwnProperty(e) && "undefined" !== typeof b[e] && (d ? c[e] = b[e] : a.setParam(e, b[e]))
            };
        a.customVars = this.customVars = {};
        a.customVars.set = this.customVars.set = function (c) {
            var b = a.getContext("page") || {}, d = b.customVars;
            if (d) {
                if (c) for (var e in c) c.hasOwnProperty(e) && (d[e] = ATInternet.Utils.completeFstLevelObj(d[e], c[e], !0))
            } else d = c;
            b.customVars = d;
            a.setContext("page", b)
        };
        a.dynamicLabel = this.dynamicLabel =
            {};
        a.dynamicLabel.set = this.dynamicLabel.set = function (c) {
            var b = a.getContext("page") || {};
            b.dynamicLabel = ATInternet.Utils.completeFstLevelObj(b.dynamicLabel, c, !0);
            a.setContext("page", b)
        };
        a.tags = this.tags = {};
        a.tags.set = this.tags.set = function (c) {
            var b = a.getContext("page") || {};
            b.tags = ATInternet.Utils.completeFstLevelObj(b.tags, c, !0);
            a.setContext("page", b)
        };
        a.customTreeStructure = this.customTreeStructure = {};
        a.customTreeStructure.set = this.customTreeStructure.set = function (c) {
            var b = a.getContext("page") || {};
            b.customTreeStructure = ATInternet.Utils.completeFstLevelObj(b.customTreeStructure, c, !0);
            a.setContext("page", b)
        };
        a.page = {};
        a.page.reset = this.reset = function () {
            a.setContext("page", void 0)
        };
        a.page.set = this.set = function (c) {
            a.dispatchSubscribe("page");
            var b = a.getContext("page") || {};
            b.name = e(b.name, c.name, "");
            b.level2 = e(b.level2, c.level2, "");
            r(b, c, "chapter1");
            r(b, c, "chapter2");
            r(b, c, "chapter3");
            b.customObject = ATInternet.Utils.completeFstLevelObj(b.customObject, c.customObject, !0);
            a.setContext("page", b)
        };
        a.page.send =
            this.send = function (b) {
                var g = !0, m = {p: k(b), s2: b.level2 || ""}, s = b.customObject, n = !1;
                if (s) {
                    var n = !0, v = ["page"], s = a.processTagObject("stc", v, s);
                    m.stc = {
                        value: ATInternet.Utils.jsonSerialize(s),
                        options: {hitType: v, encode: !0, separator: ",", truncate: !0}
                    }
                }
                s = a.getContext("page") || {};
                s.vrn && (m.vrn = s.vrn, s.vrn = void 0, a.setContext("page", s));
                v = a.getContext("InternalSearch") || {};
                "undefined" !== typeof v.keyword && (m.mc = ATInternet.Utils.cloneSimpleObject(v.keyword), "undefined" !== typeof v.resultPageNumber && (m.np = ATInternet.Utils.cloneSimpleObject(v.resultPageNumber)),
                    a.setContext("InternalSearch", void 0));
                ATInternet.Utils.isPreview() && a.getConfig("preview") && (m.pvw = 1);
                h(m, b.customVars, !0);
                f(m, b.dynamicLabel, !0);
                c(m, b.tags, !0);
                d(m, b.customTreeStructure, !0);
                v = a.getContext("campaigns") || {};
                p(m, v, !0);
                a.setContext("campaigns", void 0);
                v = null;
                b && b.hasOwnProperty("event") && (v = b.event || window.event);
                !ATInternet.Utils.isTabOpeningAction(v) && b.elem && (g = a.techClicks.manageClick(b.elem, v));
                a.manageSend(function () {
                    a.sendHit(m, null, b.callback, null, n)
                });
                s.name = e(s.name, b.name,
                    "");
                s.level2 = e(s.level2, b.level2, "");
                r(s, b, "chapter1");
                r(s, b, "chapter2");
                r(s, b, "chapter3");
                a.setContext("page", s);
                return g
            };
        a.page.onDispatch = this.onDispatch = function (b) {
            var e = a.getContext("page") || {}, g = a.getContext("InternalSearch") || {};
            a.setParam("p", k(e));
            a.setParam("s2", e.level2 || "");
            e.vrn && (a.setParam("vrn", e.vrn), e.vrn = void 0, a.setContext("page", e));
            "undefined" !== typeof g.keyword && (a.setParam("mc", ATInternet.Utils.cloneSimpleObject(g.keyword)), "undefined" !== typeof g.resultPageNumber && a.setParam("np",
                ATInternet.Utils.cloneSimpleObject(g.resultPageNumber)), a.setContext("InternalSearch", void 0));
            ATInternet.Utils.isPreview() && a.getConfig("preview") && a.setParam("pvw", 1);
            h(null, e.customVars, !1);
            f(null, e.dynamicLabel, !1);
            c(null, e.tags, !1);
            d(null, e.customTreeStructure, !1);
            g = a.getContext("campaigns") || {};
            p(null, g, !1);
            a.setContext("campaigns", void 0);
            var m = ["page"];
            (e = e.customObject) ? a.processContextObjectAndSendHit("stc", {
                hitType: m,
                encode: !0,
                separator: ",",
                truncate: !0
            }, e, b) : a.manageSend(function () {
                a.sendHit(null,
                    [["hitType", m]], b)
            })
        }
    };
    window.ATInternet.Tracker.addPlugin("Page");
}).call(window);
(function () {
    var dfltPluginCfg = {"lifetime": 182, "domainAttribution": true};
    var dfltGlobalCfg = {"redirectionLifetime": 30};
    window.ATInternet.Tracker.Plugins.IdentifiedVisitor = function (a) {
        var g = null, m = null, b = null, n = null, k = "", e = "", r = null, h = null, f = "", c = "", d = {};
        a.configPlugin("IdentifiedVisitor", dfltPluginCfg || {}, function (a) {
            d = a
        });
        a.setConfig("redirectionLifetime", dfltGlobalCfg.redirectionLifetime, !0);
        var p = function (c, b, d) {
            var f = null;
            a.plugins.exec(c, b, d, function (a) {
                f = a
            });
            return f
        }, q = function (a, c) {
            return p("Utils", a, c)
        }, l = function (c, b) {
            var d = null;
            a.plugins.exec("Storage", c, b, function (a) {
                d = a
            });
            return d
        }, w = function (a, b) {
            var d =
                l(c, [a]);
            if (null !== d) return "object" === typeof d && !(d instanceof Array);
            l(f, [a, {}, b]);
            return !0
        }, s = function (a, c) {
            w("atidvisitor", {path: "/", session: 86400 * d.lifetime}) && l(f, [["atidvisitor", a], c])
        }, u = function (c, b) {
            a.setParam(c, b, {hitType: ["all"], permanent: !0});
            s(c, b)
        }, v = function () {
            var c = function (a, c) {
                /-/.test(c) ? (a.category = c.split("-")[0], a.id = c.split("-")[1]) : a.id = c
            }, d = {category: "", id: ""};
            c(d, e || h);
            var f = {category: "", id: ""};
            c(f, k || r);
            f.id ? (f.category && u("ac", f.category), u("at", f.id)) : g && (a.setParam("at",
                g, {hitType: ["all"], permanent: !0}), b && a.setParam("ac", b, {hitType: ["all"], permanent: !0}));
            d.id ? (d.category && u("ac", d.category), u("an", d.id)) : m && a.setParam("anc", b + "-" + m, {
                hitType: ["all"],
                permanent: !0
            })
        };
        a.plugins.waitForDependencies(["Storage", "Utils"], function () {
            f = "set" + (d.domainAttribution ? "" : "Private");
            c = "get" + (d.domainAttribution ? "" : "Private");
            var p = q("getLocation", []);
            k = q("getQueryStringValue", ["xtat", p]);
            e = q("getQueryStringValue", ["xtan", p]);
            a.getConfig("redirect") ? (k || e) && w("atredir", {
                path: "/",
                end: a.getConfig("redirectionLifetime")
            }) && (e && l(f, [["atredir", "an"], e]), k && l(f, [["atredir", "at"], k])) : (r = l(c, [["atredir", "at"]]), h = l(c, [["atredir", "an"]]), l("del", [["atredir", "at"]]), l("del", [["atredir", "an"]]), g = l(c, [["atidvisitor", "at"]]), m = l(c, [["atidvisitor", "an"]]), b = l(c, [["atidvisitor", "ac"]]), n = l(c, [["atidvisitor", "vrn"]]), v(), p = "-" + a.getConfig("site") + "-", RegExp(p).test(n) || (n = (n || "") + p, s("vrn", n), p = a.getContext("page") || {}, p.vrn = 1, a.setContext("page", p)));
            a.emit("IdentifiedVisitor:Ready",
                {
                    lvl: "INFO",
                    details: {
                        storageRedirectTextual: r,
                        storageRedirectNumeric: h,
                        storageTextual: g,
                        storageNumeric: m,
                        storageCategory: b,
                        storageVrn: n
                    }
                })
        });
        a.identifiedVisitor = {};
        a.identifiedVisitor.set = this.set = function (a) {
            var c = a.id;
            a = a.category;
            "number" === typeof c ? u("an", c.toString()) : u("at", c);
            "undefined" !== typeof a && u("ac", a)
        };
        a.identifiedVisitor.unset = this.unset = function () {
            for (var c = ["an", "at", "ac"], b = 0; b < c.length; b++) a.exec("Storage", "del", [["atidvisitor", c[b]]]), a.delParam(c[b]);
            a.delParam("anc")
        }
    };
    window.ATInternet.Tracker.addPlugin("IdentifiedVisitor");
}).call(window);
(function () {
    var dfltPluginCfg = {};
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.SalesTracker = function (a) {
        var g = 0, m = function (b, g, h) {
            b.hasOwnProperty(h) && (b = b[h], a.setParam(g, void 0 === b || null === b ? "" : b + "", {hitType: ["page"]}))
        }, b = function (a) {
            for (var b in a) if (a.hasOwnProperty(b)) return !1;
            return !0
        }, n = function (a) {
            return "object" === typeof a && !(a instanceof Array)
        }, k = function (b) {
            return a.utils.manageChapters(b, "chapter", 3) + (b.name ? b.name : "")
        };
        a.order = this.order = {};
        a.cart = this.cart = {};
        a.aisle = this.aisle = {};
        a.salesTracker = {};
        a.product = this.product = {};
        a.order.set =
            this.order.set = function (e) {
                var g = !1;
                if (n(e) && !b(e)) {
                    g = !0;
                    a.dispatchSubscribe("page");
                    a.dispatchSubscribe("salesTracker");
                    m(e, "cmd", "orderId");
                    m(e, "roimt", "turnover");
                    m(e, "st", "status");
                    "boolean" === typeof e.newCustomer ? a.setParam("newcus", e.newCustomer ? "1" : "0", {hitType: ["page"]}) : e.newCustomer && "undefined" === typeof a.getParams("newcus") && a.setParam("newcus", "0", {hitType: ["page"]});
                    var h = e.amount;
                    n(h) && (m(h, "mtht", "amountTaxFree"), m(h, "mtttc", "amountTaxIncluded"), m(h, "tax", "taxAmount"));
                    h = e.delivery;
                    n(h) && (m(h, "fp", "shippingFeesTaxIncluded"), m(h, "fpht", "shippingFeesTaxFree"), m(h, "dl", "deliveryMethod"));
                    m(e, "mp", "paymentMethod");
                    h = e.discount;
                    n(h) && (m(h, "dsc", "discountTaxIncluded"), m(h, "dscht", "discountTaxFree"), m(h, "pcd", "promotionalCode"));
                    "boolean" === typeof e.confirmationRequired ? a.setParam("tp", e.confirmationRequired ? "pre1" : "", {hitType: ["page"]}) : e.confirmationRequired && "undefined" === typeof a.getParams("tp") && a.setParam("tp", "", {hitType: ["page"]});
                    e = e.orderCustomVariables;
                    if (e instanceof Array) for (h =
                                                     0; h < e.length; h++) m(e, "o" + (h + 1), h)
                }
                return g
            };
        a.cart.set = this.cart.set = function (e) {
            var g = !1;
            n(e) && !b(e) && (g = !0, a.dispatchSubscribe("page"), a.dispatchSubscribe("salesTracker"), m(e, "idcart", "cartId"), "boolean" === typeof e.isBasketPage ? "pre1" !== a.getParams("tp") && a.setParam("tp", e.isBasketPage ? "cart" : "", {hitType: ["page"]}) : e.isBasketPage && "undefined" === typeof a.getParams("tp") && a.setParam("tp", "", {hitType: ["page"]}));
            return g
        };
        a.cart.add = this.cart.add = function (e) {
            var k = !1;
            if (n(e) && !b(e) && (k = !0, e = e.product,
                n(e))) {
                g++;
                var h = g, f = e.productId ? e.productId : "", c = e.category ? e.category + "::" : "",
                    d = a.utils.manageChapters(e, "category", 6);
                d && (c = d);
                a.setParam("pdt" + h, c + f, {hitType: ["page"]});
                m(e, "qte" + h, "quantity");
                m(e, "mt" + h, "unitPriceTaxIncluded");
                m(e, "mtht" + h, "unitPriceTaxFree");
                m(e, "dsc" + h, "discountTaxIncluded");
                m(e, "dscht" + h, "discountTaxFree");
                m(e, "pcode" + h, "promotionalCode")
            }
            return k
        };
        a.aisle.set = this.aisle.set = function (e) {
            var g = !1;
            n(e) && !b(e) && (g = !0, a.dispatchSubscribe("page"), e = a.utils.manageChapters(e, "level",
                6), e = e.replace(/::$/gi, ""), a.setParam("aisl", e, {hitType: ["page"]}));
            return g
        };
        a.product.add = this.product.add = function (e) {
            var g = !1, h;
            if (h = n(e)) if (h = !b(e)) {
                a:{
                    h = ["productId"];
                    for (var f = 0; f < h.length; f++) if (void 0 === e[h[f]]) {
                        h = !0;
                        break a
                    }
                    h = !1
                }
                h = !h
            }
            h && (g = !0, h = "", (f = a.utils.manageChapters(e, "category", 6)) && (h = f), h += e.productId, e = a.getContext("product") || {}, e.viewedProducts ? e.viewedProducts.push(h) : e.viewedProducts = [h], a.setContext("product", e), a.dispatchSubscribe("salesTracker"));
            return g
        };
        a.salesTracker.onDispatch =
            this.onDispatch = function (b) {
                var g = a.getContext("product");
                if (g && g.viewedProducts) {
                    var h = ["product"], f = a.getContext("page");
                    "undefined" !== typeof f && (a.setParam("p", k(f), {hitType: h}), a.setParam("s2", f.level2 || "", {hitType: h}));
                    a.setParam("pdtl", g.viewedProducts.join("|"), {truncate: !0, hitType: h});
                    a.setParam("type", "pdt", {hitType: h});
                    a.sendHit(null, [["hitType", h]], b)
                }
            }
    };
    window.ATInternet.Tracker.addPlugin("SalesTracker");
}).call(window);
(function () {
    var dfltPluginCfg = {"collectDomain": "", "autoSalesTracker": false};
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.Ecommerce = function (a) {
        var g = null, m = "", b = null, n = null, k = null, e = {"new": {event: "b:new", tag: "new"}}, r = function () {
            this.isObject = function (c) {
                return "object" === typeof c && !(c instanceof Array)
            };
            this.objectKeysToLowercase = function (c) {
                var a = {}, b, f;
                for (f in c) c.hasOwnProperty(f) && (b = f.toLowerCase(), b = "name" === b ? "$" : b, a[b] = c[f]);
                return a
            };
            this.arrayObjectKeysToLowercase = function (c) {
                for (var a = [], f = 0; f < c.length; f++) a.push(b.objectKeysToLowercase(c[f]));
                return a
            };
            this.setValueFromKey =
                function (c, a, b, f) {
                    c.hasOwnProperty(b) && "undefined" !== typeof c[b] && (f[a] = c[b])
                }
        }, h = function () {
            this.processCartAmounts = function (c) {
                var d = c || {};
                c = Number(d.turnovertaxincluded) || 0;
                d = Number(d.turnovertaxfree) || 0;
                a.order.set({
                    turnover: c,
                    amount: {amountTaxIncluded: c, amountTaxFree: d, taxAmount: ((c || d) - (d || c)).toFixed(2)}
                })
            };
            this.processCartId = function (c, d) {
                a.cart.set({cartId: (c || {}).id, isBasketPage: d})
            };
            this.processTransactionId = function (c) {
                a.order.set({orderId: (c || {}).id, status: 3, paymentMethod: "", confirmationRequired: !1})
            };
            this.processPromotionalCode = function (c) {
                a.order.set({discount: {promotionalCode: encodeURIComponent((c || []).join("|"))}})
            };
            this.processShipping = function (c) {
                c = c || {};
                a.order.set({
                    delivery: {
                        shippingFeesTaxIncluded: c.costtaxincluded,
                        shippingFeesTaxFree: c.costtaxfree,
                        deliveryMethod: encodeURIComponent(c.delivery)
                    }
                })
            };
            this.processCustomer = function (c) {
                c = c || {};
                a.order.set({newCustomer: !!c.firstpurchase || !!c["new"]})
            };
            this.processProductCategories = function (c, a) {
                for (var b = 1; 6 >= b; b++) "undefined" !== typeof a["category" +
                b] && (c["category" + b] = a["category" + b] ? "[" + encodeURIComponent(a["category" + b]) + "]" : "")
            };
            this.processCartProducts = function (c) {
                c = c || [];
                for (var b, f = 0; f < c.length; f++) b = {
                    productId: c[f].id + (c[f].$ ? "[" + encodeURIComponent(c[f].$) + "]" : ""),
                    quantity: c[f].quantity,
                    unitPriceTaxIncluded: c[f].pricetaxincluded,
                    unitPriceTaxFree: c[f].pricetaxfree
                }, this.processProductCategories(b, c[f]), a.cart.add({product: b})
            };
            this.processViewedProducts = function (c) {
                c = c || [];
                for (var b, f = 0; f < c.length; f++) b = {
                    productId: c[f].id + (c[f].$ ? "[" +
                        encodeURIComponent(c[f].$) + "]" : "")
                }, this.processProductCategories(b, c[f]), a.product.add(b)
            }
        }, f = function () {
            this.initCartWithId = function (c) {
                var a = {};
                a["s:id"] = c;
                a["s:currency"] = "";
                a["f:turnovertaxincluded"] = 0;
                a["f:turnovertaxfree"] = 0;
                a["n:quantity"] = 0;
                a["n:nbdistinctproduct"] = 0;
                return a
            };
            this.getObjectValueFromType = function (c, b) {
                for (var f, e = {}, l = a.getContext("ecommerce") || [], h = 0; h < l.length; h++) if (f = l[h][c] || {}, f[b]) {
                    e = f[b];
                    break
                }
                return e
            };
            this.setEventFromProductsAndCart = function (c, b, f) {
                b = b || [];
                for (var e =
                    {}, l = 0; l < b.length; l++) 0 === l ? (a.event.set(c, b[l], "j:product", "ecommerce"), f && 0 !== Object.keys(f).length && a.event.set(c, f, "j:cart", "ecommerce")) : (e["j:product"] = b[l], f && 0 !== Object.keys(f).length && (e["j:cart"] = f), a.event.add(c, e, "ecommerce")), e = {}
            };
            this.setProductAwaitingPayment = function (c, a) {
                this.setEventFromProductsAndCart("product.awaiting_payment", c, a)
            };
            this.updateProductAwaitingPayment = function (c) {
                var b = c ? c["s:id"] : "";
                c = c ? c["s:version"] : "";
                if (b && c) for (var f, e = a.getContext("ecommerce") || [], l = 0; l <
                e.length; l++) if (f = e[l]["product.awaiting_payment"]) f["j:cart"] = {}, f["j:cart"]["s:id"] = b, f["j:cart"]["s:version"] = c, e[l]["product.awaiting_payment"] = f
            };
            this.setProductDisplay = function (c) {
                this.setEventFromProductsAndCart("product.display", c, null)
            };
            this.setProductClick = function (c) {
                this.setEventFromProductsAndCart("product.click", c, null)
            };
            this.setProductPageDisplay = function (c) {
                this.setEventFromProductsAndCart("product.page_display", c, null)
            };
            this.setProductAddToCart = function (c, a) {
                this.setEventFromProductsAndCart("product.add_to_cart",
                    c, a)
            };
            this.setProductRemoveFromCart = function (c, a) {
                this.setEventFromProductsAndCart("product.remove_from_cart", c, a)
            };
            this.setCartCreation = function (c) {
                var b = {};
                b["j:cart"] = c;
                a.event.set("cart.creation", b, null, "ecommerce")
            };
            this.updateCartCreation = function (c) {
                if (c = c ? c["s:id"] : "") for (var b, f = a.getContext("ecommerce") || [], e = 0; e < f.length; e++) (b = f[e]["cart.creation"]) && b["j:cart"] && (b["j:cart"]["s:id"] = c, f[e]["cart.creation"] = b)
            };
            this.setProductPurchased = function (c, b, f) {
                c = c || [];
                b = b ? b["s:id"] : "";
                f = f ? f["s:id"] :
                    "";
                for (var e = {}, l = 0; l < c.length; l++) e["j:transaction"] = {}, e["j:transaction"]["s:id"] = b, e["j:cart"] = {}, e["j:cart"]["s:id"] = f, e["j:product"] = c[l], 0 === l ? a.event.set("product.purchased", e, null, "ecommerce") : a.event.add("product.purchased", e, "ecommerce"), e = {}
            };
            this.updateProductPurchased = function (c, b) {
                for (var f = c ? c["s:id"] : "", e = b ? b["s:id"] : "", l, h = a.getContext("ecommerce") || [], g = 0; g < h.length; g++) if (l = h[g]["product.purchased"]) f && l["j:transaction"] ? l["j:transaction"]["s:id"] = f : e && l["j:cart"] && (l["j:cart"]["s:id"] =
                    e), h[g]["product.purchased"] = l
            }
        };
        a.ecommerce = {
            cartAwaitingPayment: {},
            displayProduct: {},
            displayPageProduct: {},
            addProduct: {},
            removeProduct: {},
            displayCart: {},
            updateCart: {},
            deliveryCheckout: {},
            paymentCheckout: {},
            transactionConfirmation: {}
        };
        a.ecommerce.reset = function () {
            a.event.reset("ecommerce")
        };
        a.ecommerce.cartAwaitingPayment.cart = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:cart": {}};
                    b.setValueFromKey(c, "s:id", "id", d["j:cart"]);
                    b.setValueFromKey(c, "s:currency", "currency",
                        d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxincluded", "turnovertaxincluded", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxfree", "turnovertaxfree", d["j:cart"]);
                    b.setValueFromKey(c, "d:creation_utc", "creation_utc", d["j:cart"]);
                    b.setValueFromKey(c, "n:quantity", "quantity", d["j:cart"]);
                    b.setValueFromKey(c, "n:nbdistinctproduct", "nbdistinctproduct", d["j:cart"]);
                    var f = ATInternet.Utils.uuid();
                    d["j:cart"]["s:version"] = f.v4().split("-")[0];
                    k.updateProductAwaitingPayment(d["j:cart"]);
                    a.event.set("cart.awaiting_payment",
                        d["j:cart"], "j:cart", "ecommerce");
                    g.autoSalesTracker && (n.processTransactionId(c), n.processCartId(c, !1), n.processCartAmounts(c))
                }
            }
        };
        a.ecommerce.cartAwaitingPayment.products = {
            set: function (c) {
                if (c instanceof Array) {
                    c = b.arrayObjectKeysToLowercase(c);
                    for (var a, f = [], e = k.getObjectValueFromType("cart.awaiting_payment", "j:cart"), l = 0; l < c.length; l++) a = {}, b.setValueFromKey(c[l], "s:id", "id", a), b.setValueFromKey(c[l], "s:variant", "variant", a), b.setValueFromKey(c[l], "s:$", "$", a), b.setValueFromKey(c[l], "s:brand",
                        "brand", a), b.setValueFromKey(c[l], "b:discount", "discount", a), b.setValueFromKey(c[l], "f:pricetaxincluded", "pricetaxincluded", a), b.setValueFromKey(c[l], "f:pricetaxfree", "pricetaxfree", a), b.setValueFromKey(c[l], "s:currency", "currency", a), b.setValueFromKey(c[l], "b:stock", "stock", a), b.setValueFromKey(c[l], "n:quantity", "quantity", a), b.setValueFromKey(c[l], "s:category1", "category1", a), b.setValueFromKey(c[l], "s:category2", "category2", a), b.setValueFromKey(c[l], "s:category3", "category3", a), b.setValueFromKey(c[l],
                        "s:category4", "category4", a), b.setValueFromKey(c[l], "s:category5", "category5", a), b.setValueFromKey(c[l], "s:category6", "category6", a), f.push(a);
                    a = {};
                    e["s:id"] && (a["s:id"] = e["s:id"]);
                    e["s:version"] && (a["s:version"] = e["s:version"]);
                    k.setProductAwaitingPayment(f, a);
                    g.autoSalesTracker && n.processCartProducts(c)
                }
            }
        };
        a.ecommerce.cartAwaitingPayment.shipping = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:shipping": {}};
                    b.setValueFromKey(c, "s:delivery", "delivery", d["j:shipping"]);
                    b.setValueFromKey(c,
                        "f:costtaxincluded", "costtaxincluded", d["j:shipping"]);
                    b.setValueFromKey(c, "f:costtaxfree", "costtaxfree", d["j:shipping"]);
                    a.event.set("cart.awaiting_payment", d["j:shipping"], "j:shipping", "ecommerce");
                    g.autoSalesTracker && n.processShipping(c)
                }
            }
        };
        a.ecommerce.cartAwaitingPayment.payment = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:payment": {}};
                    b.setValueFromKey(c, "s:mode", "mode", d["j:payment"]);
                    a.event.set("cart.awaiting_payment", d["j:payment"], "j:payment", "ecommerce")
                }
            }
        };
        a.ecommerce.cartAwaitingPayment.transaction = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:transaction": {}};
                    b.setValueFromKey(c, "a:s:promocode", "promocode", d["j:transaction"]);
                    b.setValueFromKey(c, "b:firstpurchase", "firstpurchase", d["j:transaction"]);
                    a.event.set("cart.awaiting_payment", d["j:transaction"], "j:transaction", "ecommerce");
                    g.autoSalesTracker && (c.promocode instanceof Array && n.processPromotionalCode(c.promocode), n.processCustomer(c))
                }
            }
        };
        a.ecommerce.displayProduct.products =
            {
                set: function (c) {
                    if (c instanceof Array) {
                        c = b.arrayObjectKeysToLowercase(c);
                        for (var a, f = [], e = 0; e < c.length; e++) a = {}, b.setValueFromKey(c[e], "s:id", "id", a), b.setValueFromKey(c[e], "s:variant", "variant", a), b.setValueFromKey(c[e], "s:$", "$", a), b.setValueFromKey(c[e], "s:brand", "brand", a), b.setValueFromKey(c[e], "b:discount", "discount", a), b.setValueFromKey(c[e], "f:pricetaxincluded", "pricetaxincluded", a), b.setValueFromKey(c[e], "f:pricetaxfree", "pricetaxfree", a), b.setValueFromKey(c[e], "s:currency", "currency", a),
                            b.setValueFromKey(c[e], "b:stock", "stock", a), b.setValueFromKey(c[e], "s:category1", "category1", a), b.setValueFromKey(c[e], "s:category2", "category2", a), b.setValueFromKey(c[e], "s:category3", "category3", a), b.setValueFromKey(c[e], "s:category4", "category4", a), b.setValueFromKey(c[e], "s:category5", "category5", a), b.setValueFromKey(c[e], "s:category6", "category6", a), f.push(a);
                        k.setProductDisplay(f)
                    }
                }
            };
        a.ecommerce.displayPageProduct.products = {
            set: function (a) {
                if (a instanceof Array) {
                    a = b.arrayObjectKeysToLowercase(a);
                    for (var d, f = [], e = 0; e < a.length; e++) d = {}, b.setValueFromKey(a[e], "s:id", "id", d), b.setValueFromKey(a[e], "s:variant", "variant", d), b.setValueFromKey(a[e], "s:$", "$", d), b.setValueFromKey(a[e], "s:brand", "brand", d), b.setValueFromKey(a[e], "b:discount", "discount", d), b.setValueFromKey(a[e], "f:pricetaxincluded", "pricetaxincluded", d), b.setValueFromKey(a[e], "f:pricetaxfree", "pricetaxfree", d), b.setValueFromKey(a[e], "s:currency", "currency", d), b.setValueFromKey(a[e], "b:stock", "stock", d), b.setValueFromKey(a[e], "s:category1",
                        "category1", d), b.setValueFromKey(a[e], "s:category2", "category2", d), b.setValueFromKey(a[e], "s:category3", "category3", d), b.setValueFromKey(a[e], "s:category4", "category4", d), b.setValueFromKey(a[e], "s:category5", "category5", d), b.setValueFromKey(a[e], "s:category6", "category6", d), f.push(d);
                    k.setProductPageDisplay(f);
                    g.autoSalesTracker && n.processViewedProducts(a)
                }
            }
        };
        a.ecommerce.addProduct.cart = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:cart": {}};
                    b.setValueFromKey(c, "s:id",
                        "id", d["j:cart"]);
                    a.event.set("product.add_to_cart", d["j:cart"], "j:cart", "ecommerce");
                    k.updateCartCreation(d["j:cart"])
                }
            }
        };
        a.ecommerce.addProduct.products = {
            set: function (a) {
                if (a instanceof Array) {
                    a = b.arrayObjectKeysToLowercase(a);
                    for (var d, f = [], e = !1, l = k.getObjectValueFromType("product.add_to_cart", "j:cart")["s:id"], l = k.initCartWithId(l), h = 0; h < a.length; h++) d = {}, b.setValueFromKey(a[h], "s:id", "id", d), b.setValueFromKey(a[h], "s:variant", "variant", d), b.setValueFromKey(a[h], "s:$", "$", d), b.setValueFromKey(a[h],
                        "s:brand", "brand", d), b.setValueFromKey(a[h], "b:discount", "discount", d), b.setValueFromKey(a[h], "f:pricetaxincluded", "pricetaxincluded", d), b.setValueFromKey(a[h], "f:pricetaxfree", "pricetaxfree", d), b.setValueFromKey(a[h], "s:currency", "currency", d), b.setValueFromKey(a[h], "b:stock", "stock", d), b.setValueFromKey(a[h], "n:quantity", "quantity", d), b.setValueFromKey(a[h], "s:category1", "category1", d), b.setValueFromKey(a[h], "s:category2", "category2", d), b.setValueFromKey(a[h], "s:category3", "category3", d), b.setValueFromKey(a[h],
                        "s:category4", "category4", d), b.setValueFromKey(a[h], "s:category5", "category5", d), b.setValueFromKey(a[h], "s:category6", "category6", d), b.setValueFromKey(a[h], "b:cartcreation", "cartcreation", d), l["s:currency"] = d["s:currency"], l["f:turnovertaxincluded"] += (Number(d["f:pricetaxincluded"]) || 0) * (Number(d["n:quantity"]) || 0), l["f:turnovertaxfree"] += (Number(d["f:pricetaxfree"]) || 0) * (Number(d["n:quantity"]) || 0), l["n:quantity"] += Number(d["n:quantity"]) || 0, l["n:nbdistinctproduct"] += 1, Boolean(Number(d["b:cartcreation"])) &&
                    (e = !0), f.push(d);
                    a = {};
                    l["s:id"] && (a["s:id"] = l["s:id"]);
                    k.setProductAddToCart(f, a);
                    e && k.setCartCreation(l)
                }
            }
        };
        a.ecommerce.removeProduct.cart = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:cart": {}};
                    b.setValueFromKey(c, "s:id", "id", d["j:cart"]);
                    a.event.set("product.remove_from_cart", d["j:cart"], "j:cart", "ecommerce")
                }
            }
        };
        a.ecommerce.removeProduct.products = {
            set: function (a) {
                if (a instanceof Array) {
                    a = b.arrayObjectKeysToLowercase(a);
                    var d, f = [], e = {};
                    e["s:id"] = k.getObjectValueFromType("product.remove_from_cart",
                        "j:cart")["s:id"];
                    for (var l = 0; l < a.length; l++) d = {}, b.setValueFromKey(a[l], "s:id", "id", d), b.setValueFromKey(a[l], "s:variant", "variant", d), b.setValueFromKey(a[l], "s:$", "$", d), b.setValueFromKey(a[l], "s:brand", "brand", d), b.setValueFromKey(a[l], "b:discount", "discount", d), b.setValueFromKey(a[l], "f:pricetaxincluded", "pricetaxincluded", d), b.setValueFromKey(a[l], "f:pricetaxfree", "pricetaxfree", d), b.setValueFromKey(a[l], "s:currency", "currency", d), b.setValueFromKey(a[l], "b:stock", "stock", d), b.setValueFromKey(a[l],
                        "n:quantity", "quantity", d), b.setValueFromKey(a[l], "s:category1", "category1", d), b.setValueFromKey(a[l], "s:category2", "category2", d), b.setValueFromKey(a[l], "s:category3", "category3", d), b.setValueFromKey(a[l], "s:category4", "category4", d), b.setValueFromKey(a[l], "s:category5", "category5", d), b.setValueFromKey(a[l], "s:category6", "category6", d), f.push(d);
                    a = {};
                    e["s:id"] && (a["s:id"] = e["s:id"]);
                    k.setProductRemoveFromCart(f, a)
                }
            }
        };
        a.ecommerce.displayCart.cart = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:cart": {}};
                    b.setValueFromKey(c, "s:id", "id", d["j:cart"]);
                    b.setValueFromKey(c, "s:currency", "currency", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxincluded", "turnovertaxincluded", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxfree", "turnovertaxfree", d["j:cart"]);
                    b.setValueFromKey(c, "n:quantity", "quantity", d["j:cart"]);
                    b.setValueFromKey(c, "n:nbdistinctproduct", "nbdistinctproduct", d["j:cart"]);
                    a.event.set("cart.display", d["j:cart"], "j:cart", "ecommerce");
                    g.autoSalesTracker && n.processCartId(c,
                        !0)
                }
            }
        };
        a.ecommerce.displayCart.products = {
            set: function (a) {
                g.autoSalesTracker && a instanceof Array && (a = b.arrayObjectKeysToLowercase(a), n.processCartProducts(a))
            }
        };
        a.ecommerce.updateCart.cart = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:cart": {}};
                    b.setValueFromKey(c, "s:id", "id", d["j:cart"]);
                    b.setValueFromKey(c, "s:currency", "currency", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxincluded", "turnovertaxincluded", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxfree", "turnovertaxfree",
                        d["j:cart"]);
                    b.setValueFromKey(c, "n:quantity", "quantity", d["j:cart"]);
                    b.setValueFromKey(c, "n:nbdistinctproduct", "nbdistinctproduct", d["j:cart"]);
                    a.event.set("cart.update", d["j:cart"], "j:cart", "ecommerce")
                }
            }
        };
        a.ecommerce.deliveryCheckout.cart = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:cart": {}};
                    b.setValueFromKey(c, "s:id", "id", d["j:cart"]);
                    b.setValueFromKey(c, "s:currency", "currency", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxincluded", "turnovertaxincluded", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxfree", "turnovertaxfree", d["j:cart"]);
                    b.setValueFromKey(c, "n:quantity", "quantity", d["j:cart"]);
                    b.setValueFromKey(c, "n:nbdistinctproduct", "nbdistinctproduct", d["j:cart"]);
                    a.event.set("cart.delivery", d["j:cart"], "j:cart", "ecommerce")
                }
            }
        };
        a.ecommerce.deliveryCheckout.shipping = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:shipping": {}};
                    b.setValueFromKey(c, "s:delivery", "delivery", d["j:shipping"]);
                    b.setValueFromKey(c, "f:costtaxincluded", "costtaxincluded",
                        d["j:shipping"]);
                    b.setValueFromKey(c, "f:costtaxfree", "costtaxfree", d["j:shipping"]);
                    a.event.set("cart.delivery", d["j:shipping"], "j:shipping", "ecommerce")
                }
            }
        };
        a.ecommerce.paymentCheckout.cart = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:cart": {}};
                    b.setValueFromKey(c, "s:id", "id", d["j:cart"]);
                    b.setValueFromKey(c, "s:currency", "currency", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxincluded", "turnovertaxincluded", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxfree", "turnovertaxfree",
                        d["j:cart"]);
                    b.setValueFromKey(c, "n:quantity", "quantity", d["j:cart"]);
                    b.setValueFromKey(c, "n:nbdistinctproduct", "nbdistinctproduct", d["j:cart"]);
                    a.event.set("cart.payment", d["j:cart"], "j:cart", "ecommerce")
                }
            }
        };
        a.ecommerce.paymentCheckout.shipping = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:shipping": {}};
                    b.setValueFromKey(c, "s:delivery", "delivery", d["j:shipping"]);
                    b.setValueFromKey(c, "f:costtaxincluded", "costtaxincluded", d["j:shipping"]);
                    b.setValueFromKey(c, "f:costtaxfree",
                        "costtaxfree", d["j:shipping"]);
                    a.event.set("cart.payment", d["j:shipping"], "j:shipping", "ecommerce")
                }
            }
        };
        a.ecommerce.transactionConfirmation.cart = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:cart": {}};
                    b.setValueFromKey(c, "s:id", "id", d["j:cart"]);
                    b.setValueFromKey(c, "s:currency", "currency", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxincluded", "turnovertaxincluded", d["j:cart"]);
                    b.setValueFromKey(c, "f:turnovertaxfree", "turnovertaxfree", d["j:cart"]);
                    b.setValueFromKey(c, "d:creation_utc",
                        "creation_utc", d["j:cart"]);
                    b.setValueFromKey(c, "n:quantity", "quantity", d["j:cart"]);
                    b.setValueFromKey(c, "n:nbdistinctproduct", "nbdistinctproduct", d["j:cart"]);
                    a.event.set("transaction.confirmation", d["j:cart"], "j:cart", "ecommerce");
                    k.updateProductPurchased(null, d["j:cart"]);
                    g.autoSalesTracker && (n.processCartId(c, !1), n.processCartAmounts(c))
                }
            }
        };
        a.ecommerce.transactionConfirmation.discount = {
            set: function (c) {
                if (c instanceof Array) {
                    var b = {"j:transaction": {}};
                    b["j:transaction"]["a:s:promocode"] = c;
                    a.event.set("transaction.confirmation",
                        b["j:transaction"], "j:transaction", "ecommerce");
                    g.autoSalesTracker && n.processPromotionalCode(c)
                }
            }
        };
        a.ecommerce.transactionConfirmation.transaction = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:transaction": {}};
                    b.setValueFromKey(c, "s:id", "id", d["j:transaction"]);
                    b.setValueFromKey(c, "a:s:promocode", "promocode", d["j:transaction"]);
                    b.setValueFromKey(c, "b:firstpurchase", "firstpurchase", d["j:transaction"]);
                    a.event.set("transaction.confirmation", d["j:transaction"], "j:transaction",
                        "ecommerce");
                    k.updateProductPurchased(d["j:transaction"], null);
                    g.autoSalesTracker && (n.processTransactionId(c), c.promocode instanceof Array && n.processPromotionalCode(c.promocode), n.processCustomer(c))
                }
            }
        };
        a.ecommerce.transactionConfirmation.shipping = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:shipping": {}};
                    b.setValueFromKey(c, "s:delivery", "delivery", d["j:shipping"]);
                    b.setValueFromKey(c, "f:costtaxincluded", "costtaxincluded", d["j:shipping"]);
                    b.setValueFromKey(c, "f:costtaxfree",
                        "costtaxfree", d["j:shipping"]);
                    a.event.set("transaction.confirmation", d["j:shipping"], "j:shipping", "ecommerce");
                    g.autoSalesTracker && n.processShipping(c)
                }
            }
        };
        a.ecommerce.transactionConfirmation.payment = {
            set: function (c) {
                if (b.isObject(c)) {
                    c = b.objectKeysToLowercase(c);
                    var d = {"j:payment": {}};
                    b.setValueFromKey(c, "s:mode", "mode", d["j:payment"]);
                    a.event.set("transaction.confirmation", d["j:payment"], "j:payment", "ecommerce")
                }
            }
        };
        a.ecommerce.transactionConfirmation.customer = {
            set: function (c) {
                if (b.isObject(c)) {
                    c =
                        b.objectKeysToLowercase(c);
                    var d = {"j:transaction": {}};
                    b.setValueFromKey(c, "b:firstpurchase", e["new"].tag, d["j:transaction"]);
                    a.event.set("transaction.confirmation", d["j:transaction"], "j:transaction", "ecommerce");
                    g.autoSalesTracker && n.processCustomer(c)
                }
            }
        };
        a.ecommerce.transactionConfirmation.products = {
            set: function (a) {
                if (a instanceof Array) {
                    a = b.arrayObjectKeysToLowercase(a);
                    for (var d, f = [], e = k.getObjectValueFromType("transaction.confirmation", "j:cart"), l = k.getObjectValueFromType("transaction.confirmation",
                        "j:transaction"), h = 0; h < a.length; h++) d = {}, b.setValueFromKey(a[h], "s:id", "id", d), b.setValueFromKey(a[h], "s:variant", "variant", d), b.setValueFromKey(a[h], "s:$", "$", d), b.setValueFromKey(a[h], "s:brand", "brand", d), b.setValueFromKey(a[h], "b:discount", "discount", d), b.setValueFromKey(a[h], "f:pricetaxincluded", "pricetaxincluded", d), b.setValueFromKey(a[h], "f:pricetaxfree", "pricetaxfree", d), b.setValueFromKey(a[h], "s:currency", "currency", d), b.setValueFromKey(a[h], "b:stock", "stock", d), b.setValueFromKey(a[h], "n:quantity",
                        "quantity", d), b.setValueFromKey(a[h], "s:category1", "category1", d), b.setValueFromKey(a[h], "s:category2", "category2", d), b.setValueFromKey(a[h], "s:category3", "category3", d), b.setValueFromKey(a[h], "s:category4", "category4", d), b.setValueFromKey(a[h], "s:category5", "category5", d), b.setValueFromKey(a[h], "s:category6", "category6", d), f.push(d);
                    k.setProductPurchased(f, l, e);
                    g.autoSalesTracker && n.processCartProducts(a)
                }
            }
        };
        (function () {
            a.configPlugin("Ecommerce", dfltPluginCfg || {}, function (a) {
                g = a
            });
            g.collectDomain &&
            (m = String(g.collectDomain));
            b = new r;
            n = new h;
            k = new f
        })();
        a.ecommerce.onDispatch = function (c) {
            a.event.onDispatch(c, "ecommerce", m)
        }
    };
    window.ATInternet.Tracker.addPlugin("Ecommerce");
}).call(window);
(function () {
    var dfltPluginCfg = {
        "hitParameter": "col",
        "hitValue": "2",
        "defaultCollectSubdomain": "collect-euw1",
        "defaultCollectDomains": [".xiti.com", ".ati-host.net"]
    };
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.Event = function (a) {
        var g = {}, m = "", b = function (a) {
            for (var b = [], c = 0; c < a.length; c++) for (var d in a[c]) a[c].hasOwnProperty(d) && b.push({
                name: d,
                data: a[c][d]
            });
            return ATInternet.Utils.jsonSerialize(b)
        }, n = function (e, f) {
            var c = f || "event";
            a.setParam("events", b(e), {hitType: [c], encode: !0, truncate: !0, separator: ","})
        }, k = function (a) {
            return "object" === typeof a && !(a instanceof Array)
        }, e = function (a, b) {
            return a = a instanceof Array && b instanceof Array ? a.concat(b) : k(a) && k(b) ? ATInternet.Utils.completeFstLevelObj(a,
                b, !0) : b
        };
        a.event = {};
        a.event.reset = function (b) {
            b = b || "event";
            a.getContext(b);
            a.setContext(b, void 0)
        };
        a.event.send = function (e, f, c) {
            var d = !0, k = "event";
            c && c.origin && (k = c.origin);
            var q = {};
            q[e] = f;
            var l = {};
            l[g.hitParameter] = {value: g.hitValue, options: {multihit: !0}};
            l.events = {value: b([q]), options: {encode: !0, truncate: !0, separator: ","}};
            ATInternet.Utils.isPreview() && a.getConfig("preview") && (l.pvw = 1);
            e = null;
            c && c.hasOwnProperty("event") && (e = c.event || window.event);
            !ATInternet.Utils.isTabOpeningAction(e) && c && c.elem &&
            (d = a.techClicks.manageClick(c.elem, e));
            a.manageSend(function () {
                var b, d;
                c && (b = c.callback, d = c.domain);
                d = d || m;
                a.sendHit(l, [["hitType", [k]]], b, d, !0)
            });
            return d
        };
        a.event.set = function (b, f, c, d) {
            d = d || "event";
            a.setParam(g.hitParameter, g.hitValue, {multihit: !0, hitType: [d]});
            a.dispatchSubscribe(d);
            for (var p = a.getContext(d) || [], q = !1, l = 0; l < p.length; l++) p[l][b] && (q = !0, c ? k(p[l][b]) && (p[l][b][c] = e(p[l][b][c], f)) : p[l][b] = e(p[l][b], f));
            q || (q = {}, c ? (q[b] = {}, q[b][c] = f) : q[b] = f, p.push(q));
            a.setContext(d, p)
        };
        a.event.add =
            function (b, f, c) {
                c = c || "event";
                var d = a.getContext(c) || [], e = {};
                e[b] = f;
                d.push(e);
                a.setContext(c, d)
            };
        a.event.onDispatch = function (b, f, c) {
            var d = f || "event";
            f = a.getContext(d);
            "undefined" !== typeof f && n(f, d);
            a.setContext(d, void 0);
            ATInternet.Utils.isPreview() && a.getConfig("preview") && a.setParam("pvw", 1, {hitType: [d]});
            a.manageSend(function () {
                c = c || m;
                a.sendHit(null, [["hitType", [d]]], b, c)
            })
        };
        var r = function () {
            for (var b = g.defaultCollectSubdomain + g.defaultCollectDomains[0], f = a.utils.getCollectDomain(), c = 0; c < g.defaultCollectDomains.length; c++) if (-1 <
                f.indexOf(g.defaultCollectDomains[c])) {
                b = g.defaultCollectSubdomain + g.defaultCollectDomains[c];
                break
            }
            return b
        };
        (function () {
            a.plugins.waitForDependencies(["Utils"], function () {
                a.configPlugin("Event", dfltPluginCfg || {}, function (a) {
                    g = a
                });
                m = r()
            })
        })()
    };
    window.ATInternet.Tracker.addPlugin("Event");
}).call(window);
(function () {
    var dfltPluginCfg = {};
    var dfltGlobalCfg = {};
    window.ATInternet.Tracker.Plugins.OnSiteAds = function (a) {
        var g = "", m = function (b) {
            return a.utils.manageChapters(b, "chapter", 3) + (b.name ? b.name : "")
        }, b = function (a, c) {
            return a[c] ? a[c] : ""
        }, n = function (a, c) {
            var d = b(a, c);
            if (d) {
                var e = b(a, "prefix");
                if (d.campaignId) {
                    var e = e || "PUB", g = b(d, "campaignId"), l = b(d, "creation"), h = b(d, "variant"),
                        k = b(d, "format"), m = b(d, "generalPlacement"), r = b(d, "detailedPlacement"),
                        n = b(d, "advertiserId"), d = b(d, "url");
                    return e + "-" + g + "-" + l + "-" + h + "-" + k + "-" + m + "-" + r + "-" + n + "-" + d
                }
                if (d.adId) return e =
                    e || "INT", g = b(d, "adId"), l = b(d, "format"), d = b(d, "productId"), e + "-" + g + "-" + l + "||" + d
            }
            return ""
        }, k = function (b) {
            var c = ["onSiteAdsImpression"], d = {};
            d.ati = {value: n(b, "impression"), options: {hitType: c, truncate: !0}};
            d.type = "AT";
            ATInternet.Utils.isPreview() && a.getConfig("preview") && (d.pvw = 1);
            var e = b.customObject;
            e && (e = a.processTagObject("stc", c, e), d.stc = {
                value: ATInternet.Utils.jsonSerialize(e),
                options: {hitType: c, encode: !0, separator: ",", truncate: !0}
            });
            a.manageSend(function () {
                a.sendHit(d, [["hitType", c]], b.callback,
                    null, !0)
            })
        }, e = function (b, c) {
            var d = a.buffer.get("ati", !0) || {};
            d.value = "string" === typeof d.value ? [d.value] : d.value || [];
            d.options = d.options || {truncate: !0, hitType: [c, "page"]};
            d.value.push(b);
            a.buffer.set("ati", d.value, d.options)
        }, r = function (b, c) {
            b.click ? a.setParam("atc", n(b, "click"), {
                truncate: !0,
                hitType: [c, "page"]
            }) : b.impression && a.setParam("ati", n(b, "impression"), {truncate: !0, hitType: [c, "page"]});
            if (b.customObject) {
                a.setContext("onsiteads", {customObject: b.customObject});
                var d = a.getContext("page") || {};
                d.customObject = ATInternet.Utils.completeFstLevelObj(d.customObject, b.customObject, !1);
                a.setContext("page", d)
            }
            a.dispatchSubscribe("onSiteAds")
        };
        a.selfPromotion = this.selfPromotion = {};
        a.publisher = this.publisher = {};
        a.publisher.set = this.publisher.set = function (a) {
            r(a, "publisher")
        };
        a.selfPromotion.set = this.selfPromotion.set = function (a) {
            r(a, "selfPromotion")
        };
        a.publisher.add = this.publisher.add = function (b) {
            e(n(b, "impression"), "publisher");
            a.dispatchSubscribe("onSiteAds")
        };
        a.selfPromotion.add = this.selfPromotion.add =
            function (b) {
                e(n(b, "impression"), "selfPromotion");
                a.dispatchSubscribe("onSiteAds")
            };
        var h = this.advertEvent = function (b) {
            var c = !0, d = null;
            b && b.hasOwnProperty("event") && (d = b.event || window.event);
            !ATInternet.Utils.isTabOpeningAction(d) && b.elem && (c = a.techClicks.manageClick(b.elem, d));
            if (b.click) {
                var d = ["onSiteAdsClick"], e = a.getContext("page") || {}, g = {};
                g.atc = {value: n(b, "click"), options: {truncate: !0}};
                g.type = "AT";
                g.patc = m(e);
                g.s2atc = e.level2 || "";
                if (e = b.customObject) e = a.processTagObject("stc", d, e), g.stc = {
                    value: ATInternet.Utils.jsonSerialize(e),
                    options: {hitType: d, encode: !0, separator: ",", truncate: !0}
                };
                a.sendHit(g, [["hitType", d]], b.callback, null, !0)
            } else b.impression && k(b);
            return c
        };
        a.publisher.send = this.publisher.send = function (a) {
            return h(a)
        };
        a.selfPromotion.send = this.selfPromotion.send = function (a) {
            return h(a)
        };
        a.onSiteAds = {};
        a.onSiteAds.onDispatch = this.onDispatch = function (b) {
            if (!a.dispatchSubscribed("page")) {
                a.setParam("type", "AT", {hitType: ["publisher", "selfPromotion"]});
                var c = a.getContext("page") || {};
                a.getParam("atc") && (a.setParam("patc",
                    m(c), {hitType: ["publisher", "selfPromotion"]}), a.setParam("s2atc", c.level2 || "", {hitType: ["publisher", "selfPromotion"]}));
                ATInternet.Utils.isPreview() && a.getConfig("preview") && a.setParam("pvw", 1);
                var d = ["publisher", "selfPromotion"];
                (c = (a.getContext("onsiteads") || {}).customObject) ? a.processContextObjectAndSendHit("stc", {
                    hitType: d,
                    encode: !0,
                    separator: ",",
                    truncate: !0
                }, c, b) : a.manageSend(function () {
                    a.sendHit(null, [["hitType", d]], b)
                })
            }
        };
        a.plugins.waitForDependencies(["Utils", "TechClicks"], function () {
            g = document.location.href;
            a.plugins.exec("Utils", "getQueryStringValue", ["xtatc", g], function (b) {
                b && a.setParam("atc", b, {hitType: ["publisher", "selfPromotion", "page"]})
            });
            a.emit("OnSiteAds:Ready", {lvl: "INFO", details: {href: g}})
        })
    };
    window.ATInternet.Tracker.addPlugin("OnSiteAds");
}).call(window);
if (typeof window.ATInternet.onTrackerLoad === 'function') {
    window.ATInternet.onTrackerLoad();
}