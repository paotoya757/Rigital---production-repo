/* ========================================================================
 * Copyright 2014 grupo
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 grupo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201410152247

*/

package co.edu.uniandes.csw.grupo.wiki.master.persistence.entity;

import co.edu.uniandes.csw.grupo.estudiante.persistence.entity.EstudianteEntity;
import co.edu.uniandes.csw.grupo.wiki.persistence.entity.WikiEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(WikidueniosEntityId.class)
@NamedQueries({
    @NamedQuery(name = "WikidueniosEntity.getByMasterId", query = "SELECT  u FROM WikidueniosEntity u WHERE u.wikiId=:wikiId"),
    @NamedQuery(name = "WikidueniosEntity.deleteWikidueniosEntity", query = "DELETE FROM WikidueniosEntity u WHERE u.wikiId=:wikiId and  u.dueniosId=:dueniosId")
})
public class WikidueniosEntity implements Serializable {

    @Id
    @Column(name = "wikiId")
    private Long wikiId;
    @Id
    @Column(name = "dueniosId")
    private Long dueniosId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "wikiId", referencedColumnName = "id")
    @JoinFetch
    private EstudianteEntity wikiIdEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dueniosId", referencedColumnName = "id")
    @JoinFetch
    private EstudianteEntity dueniosIdEntity; 

    public WikidueniosEntity() {
    }

    public WikidueniosEntity(Long wikiId, Long dueniosId) {
        this.wikiId =wikiId;
        this.dueniosId = dueniosId;
    }

    public Long getWikiId() {
        return wikiId;
    }

    public void setWikiId(Long wikiId) {
        this.wikiId = wikiId;
    }

    public Long getDueniosId() {
        return dueniosId;
    }

    public void setDueniosId(Long dueniosId) {
        this.dueniosId = dueniosId;
    }

    public EstudianteEntity getWikiIdEntity() {
        return wikiIdEntity;
    }

    public void setWikiIdEntity(EstudianteEntity wikiIdEntity) {
        this.wikiIdEntity = wikiIdEntity;
    }

    public EstudianteEntity getDueniosIdEntity() {
        return dueniosIdEntity;
    }

    public void setDueniosIdEntity(EstudianteEntity dueniosIdEntity) {
        this.dueniosIdEntity = dueniosIdEntity;
    }

}
